package ensonglopedia.backend;

import java.io.*;

import java.util.*;
import java.util.stream.Collectors;

public class ensonglopedia {
    private List<Song> Songs;
    private String directory = "/src/main/java/ensonglopedia/storage/Ensonglopedia.csv";
    private String basedirectory = new File(".").getAbsoluteFile().toString();

    public ensonglopedia(){

        Songs = new ArrayList<Song>();
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
                Song tempSong = new Song();

                tempSong.setTitle(stringArray[0]);
                tempSong.getAlbumDet().setArtist(stringArray[1]);
                tempSong.getAlbumDet().setMusicBook(stringArray[2]);

                Songs.add(tempSong);
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
        Song song = new Song(Title,Artist,MusicBook);
        //Song songTemp = new Song("","",MusicBook);
        Song searchResults = searchSongs(song);

        if(searchResults!=null){
            searchResults.setTitle(Title);
            searchResults.getAlbumDet().setArtist(Artist);
            System.out.println("Error song already inputted");
        }
        else{
            Songs.add(song);
            System.out.println("check2");
        }
        saveClass();
    }
    public void printSong(Song song){
        System.out.println(song.getTitle()+"	"+song.getAlbumDet().getArtist()+"	"+song.getAlbumDet().getMusicBook());
    }

    public Song searchSongs(Song songForSearch){

        AlbumDet album1  = songForSearch.getAlbumDet();

        for (Iterator i = Songs.iterator(); i.hasNext();){
            Song song = (Song)i.next();
            AlbumDet album2 = song.getAlbumDet();

            if(songForSearch.getTitle().equals(song.getTitle()))
                if(album1.getArtist().equals(album2.getArtist()))
                    if(album1.getMusicBook().equals(album2.getMusicBook())){
                        return song;
                    }
        }
        return null;
    }

    public void saveClass(){
        try //attempt this method
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(basedirectory+directory));

            for (Iterator i = Songs.iterator(); i.hasNext();){
                Song song = (Song)i.next();
                bw.write(song.toString());
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

    public List <Song> searchAlbums(AlbumDet album1){
        List <Song> searchResults = new LinkedList <Song>();
        for (Iterator i = Songs.iterator(); i.hasNext();){
            Song song = (Song)i.next();
            AlbumDet album2 = song.getAlbumDet();

            if(album1.getArtist()!=album2.getArtist())
                continue;
            if(album1.getMusicBook()!=album2.getMusicBook())
                continue;
            searchResults.add(song);
        }
        return searchResults;
    }

    public void sortMusicBooks(Criteria criteria){
        //sort linked list;

        Collections.sort(Songs,new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                String temp1;
                String temp2;
                switch (criteria) {
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
    public void deleteBooks(Song songToDelete){
        AlbumDet album1  = songToDelete.getAlbumDet();
        int nextIndex =0;
        List<Integer> indices = new LinkedList <Integer>();
        for (Iterator i = Songs.iterator(); i.hasNext();){
            Song song = (Song)i.next();
            AlbumDet album2 = song.getAlbumDet();

            if(album1.getMusicBook().equals(album2.getMusicBook())){
                indices.add(nextIndex);
            }
            nextIndex +=1;
        }
        for(int i=indices.size()-1; i>=0;i-- ){
            nextIndex = indices.get(i);
            System.out.println(nextIndex);
            Songs.remove(nextIndex);
        }

    }
    public String[] readMusicBooks(){
        Set<String> noDuplicates = Songs
                .stream()
                .map((Song song1) -> song1.getAlbumDet().getMusicBook())
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
