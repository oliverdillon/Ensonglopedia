package com.ensonglopedia.repository;

import com.ensonglopedia.dao.SongDao;
import com.ensonglopedia.entities.AlbumDetailsObject;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.SortingCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SongBookRepositoryImpl implements SongBookRepository {

    private List<SongObject> songObjects= new ArrayList<SongObject>();

    @Autowired
    private SongDao songDao;

    public SongBookRepositoryImpl() {
    }


    public void loadSongs()
    {
        songDao.loadSongs(songObjects);
    }

    public void saveSongs(){

        songDao.saveSongs(songObjects);
    }

    public void addSong(String Title,String Artist,String Album){
        SongObject songObject = new SongObject(Title,Artist,Album);
        SongObject songObjectTemp = new SongObject("","",Album);
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
        saveSongs();
    }

    public List<SongObject> getSongObjects() {
        return songObjects;
    }


    public SongObject searchSongs(SongObject songObjectForSearch){

        AlbumDetailsObject album1  = songObjectForSearch.getAlbumDet();

        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            AlbumDetailsObject album2 = songObject.getAlbumDet();

            if(songObjectForSearch.getTitle().equals(songObject.getTitle()))
                if(album1.getArtist().equals(album2.getArtist()))
                    if(album1.getAlbum().equals(album2.getAlbum())){
                        return songObject;
                    }
        }
        return null;
    }

    public List <SongObject> searchAlbums(AlbumDetailsObject album1){
        List <SongObject> searchResults = new LinkedList<SongObject>();
        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            AlbumDetailsObject album2 = songObject.getAlbumDet();

            if(album1.getArtist()!=album2.getArtist())
                continue;
            if(album1.getAlbum()!=album2.getAlbum())
                continue;
            searchResults.add(songObject);
        }
        return searchResults;
    }

    public void sortAlbums(SortingCriteria sortingCriteria){
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
                        temp1 = o1.getAlbumDet().getAlbum();
                        temp2 = o2.getAlbumDet().getAlbum();
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
    public void deleteAlbums(SongObject songObjectToDelete){
        AlbumDetailsObject album1  = songObjectToDelete.getAlbumDet();
        int nextIndex =0;
        List<Integer> indices = new LinkedList <Integer>();
        for (Iterator i = songObjects.iterator(); i.hasNext();){
            SongObject songObject = (SongObject)i.next();
            AlbumDetailsObject album2 = songObject.getAlbumDet();

            if(album1.getAlbum().equals(album2.getAlbum())){
                indices.add(nextIndex);
            }
            nextIndex +=1;
        }
        for(int i=indices.size()-1; i>=0;i-- ){
            nextIndex = indices.get(i);
            songObjects.remove(nextIndex);
        }
        System.out.println("Successfully deleted "+indices.size()+" songs");
        saveSongs();
    }
    public String[] readAlbums(){
        Set<String> noDuplicates = songObjects
                .stream()
                .map((SongObject songObject1) -> songObject1.getAlbumDet().getAlbum())
                .collect(Collectors.toSet());
        List<String> sortedArray = noDuplicates.stream().sorted().collect(Collectors.toList());


        int length = sortedArray.size()+1;
        int nextIndex = 1;

        String[] albums = new String[length];

        for (String song :sortedArray){
            albums[nextIndex] = song;
            nextIndex++;
        }

        albums[0] = "Album";

        return 	albums;
    }
}
