package com.ensonglopedia.service;

import com.ensonglopedia.entities.MusicBookDetailsObject;
import com.ensonglopedia.entities.SongObject;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationService {
    private List<SongObject> songObjects;
    private String directory = "/src/main/java/com/ensonglopedia/dao/Ensonglopedia.csv";
    private String basedirectory = new File(".").getAbsoluteFile().toString();

    public ApplicationService(){

        songObjects = new ArrayList<SongObject>();
        this.loadSongs();
    }

    public void loadSongs()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(basedirectory+directory));

            String sReadLine = reader.readLine();
            while (sReadLine!= null)
            {
                String[] stringArray = sReadLine.split(",");
                SongObject tempSongObject = new SongObject();

                tempSongObject.setTitle(stringArray[0]);
                tempSongObject.getAlbumDet().setArtist(stringArray[1]);
                tempSongObject.getAlbumDet().setMusicBook(stringArray[2]);

                songObjects.add(tempSongObject);
                sReadLine = reader.readLine();
            }
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Ensonglopedia does not exist");
            //this outputs the message to tell the user that something has gone wrong
        }
    }

    public void addSong(String Title,String Artist,String MusicBook){
        SongObject songObject = new SongObject(Title,Artist,MusicBook);
        SongObject songObjectTemp = new SongObject("","",MusicBook);
        SongObject searchResults = searchSongs(songObjectTemp);

        if(searchResults!=null){
            searchResults.setTitle(Title);
            searchResults.getAlbumDet().setArtist(Artist);
            System.out.println("Overwrite temp song");
        }
        else{
            songObjects.add(songObject);
            System.out.println("Successfully added a new song");
        }
        saveClass();
    }
    public void printSong(SongObject songObject){
        System.out.println(songObject.getTitle()+"	"+ songObject.getAlbumDet().getArtist()+"	"+ songObject.getAlbumDet().getMusicBook());
    }

    public SongObject searchSongs(SongObject songObjectForSearch){

        MusicBookDetailsObject album1  = songObjectForSearch.getAlbumDet();

        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            MusicBookDetailsObject album2 = songObject.getAlbumDet();

            if(songObjectForSearch.getTitle().equals(songObject.getTitle()))
                if(album1.getArtist().equals(album2.getArtist()))
                    if(album1.getMusicBook().equals(album2.getMusicBook())){
                        return songObject;
                    }
        }
        return null;
    }

    public void saveClass(){
        try //attempt this method
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(basedirectory+directory));

            for (Iterator i = songObjects.iterator(); i.hasNext();){
                SongObject songObject = (SongObject)i.next();
                bw.write(songObject.toString());
                bw.newLine();
                //saves an array in the file
            }
            bw.close(); // closes the file
        }
        catch(Exception e) //If that did not work try this
        {
            System.out.println("Error when writing to file");
            //this outputs the message to tell the user that something has gone wrong

        }

    }

    public List <SongObject> searchAlbums(MusicBookDetailsObject album1){
        List <SongObject> searchResults = new LinkedList <SongObject>();
        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            MusicBookDetailsObject album2 = songObject.getAlbumDet();

            if(album1.getArtist()!=album2.getArtist())
                continue;
            if(album1.getMusicBook()!=album2.getMusicBook())
                continue;
            searchResults.add(songObject);
        }
        return searchResults;
    }

    public void sortMusicBooks(SortingCriteria sortingCriteria){
        //sort linked list;

        Collections.sort(songObjects,new Comparator<SongObject>() {
            @Override
            public int compare(SongObject o1, SongObject o2) {
                String temp1;
                String temp2;
                switch (sortingCriteria) {
                    case Title:
                        temp1 = o1.getTitle();
                        temp2 = o2.getTitle();
                        break;
                    case Artist:
                        temp1 = o1.getAlbumDet().getArtist();
                        temp2 = o2.getAlbumDet().getArtist();
                        break;
                    case Album:
                        temp1 = o1.getAlbumDet().getMusicBook();
                        temp2 = o2.getAlbumDet().getMusicBook();
                        break;
                    default:
                        temp1 = o1.getTitle();
                        temp2 = o2.getTitle();
                }
                if (temp1.charAt(0) > temp2.charAt(0)) {
                    return 1;
                }
                else
                    return 0;
            }
        });
    }
    public void deleteBooks(SongObject songObjectToDelete){
        MusicBookDetailsObject album1  = songObjectToDelete.getAlbumDet();
        int nextIndex =0;
        List<Integer> indices = new LinkedList <Integer>();
        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            MusicBookDetailsObject album2 = songObject.getAlbumDet();

            if(album1.getMusicBook().equals(album2.getMusicBook())){
                indices.add(nextIndex);
            }
            nextIndex +=1;
        }
        for(int i=indices.size()-1; i>=0;i-- ){
            nextIndex = indices.get(i);
            songObjects.remove(nextIndex);
        }
        System.out.println("Successfully deleted "+indices.size()+" songs");
        saveClass();
    }
    public String[] readMusicBooks(){
        Set<String> noDuplicates = songObjects
                .stream()
                .map((SongObject songObject1) -> songObject1.getAlbumDet().getMusicBook())
                .collect(Collectors.toSet());
        List<String> sortedArray = noDuplicates.stream().sorted().collect(Collectors.toList());


        int length = sortedArray.size()+1;
        int nextIndex = 0;

        String[] musicBooks = new String[length];

        for (String song :sortedArray){
            musicBooks[nextIndex] = song;
            nextIndex++;
        }

        musicBooks[length-1] = "Select Music Book";

        return 	musicBooks;
    }
}
