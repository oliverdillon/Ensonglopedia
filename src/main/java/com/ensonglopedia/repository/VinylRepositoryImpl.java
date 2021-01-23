package com.ensonglopedia.repository;

import com.ensonglopedia.dao.VinylDao;
import com.ensonglopedia.dao.VinylDaoImpl;
import com.ensonglopedia.entities.AlbumDetailsObject;
import com.ensonglopedia.entities.VinylObject;
import com.ensonglopedia.service.SortingCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class VinylRepositoryImpl implements VinylRepository {

    private List<VinylObject> vinylObjects = new ArrayList<VinylObject>();

    @Autowired
    private VinylDao vinylDao;

    public VinylRepositoryImpl() {
    }


    public void loadVinyls()
    {
        vinylDao.loadVinyls(vinylObjects);
    }

    public void saveVinyls(){
        vinylDao.saveVinyls(vinylObjects);
    }

    public void addVinyl(String Artist,String Album,String ReleaseDate){
        VinylObject vinylObject = new VinylObject(Artist,Album,ReleaseDate);
        VinylObject vinylObjectTemp = new VinylObject("",Album,"");
        VinylObject searchResults = searchVinyls(vinylObjectTemp);

        if(searchResults!=null){
            searchResults.setReleaseDate(ReleaseDate);
            searchResults.getAlbumDet().setArtist(Artist);
            System.out.println("Overwrite temp vinyl");
        }
        else{
            vinylObjects.add(vinylObject);
            System.out.println("Successfully added a new vinyl");
        }
        saveVinyls();
    }

    public List<VinylObject> getVinylObjects() {
        return vinylObjects;
    }


    public VinylObject searchVinyls(VinylObject vinylObjectForSearch){

        AlbumDetailsObject album1  = vinylObjectForSearch.getAlbumDet();

        for (Iterator i = vinylObjects.iterator(); i.hasNext();){
            VinylObject vinylObject = (VinylObject)i.next();
            AlbumDetailsObject album2 = vinylObject.getAlbumDet();

            if(vinylObjectForSearch.getReleaseDate().equals(vinylObject.getReleaseDate()))
                if(album1.getArtist().equals(album2.getArtist()))
                    if(album1.getAlbum().equals(album2.getAlbum())){
                        return vinylObject;
                    }
        }
        return null;
    }

    public List <VinylObject> searchAlbums(AlbumDetailsObject album1){
        List <VinylObject> searchResults = new LinkedList<VinylObject>();
        for (Iterator i = vinylObjects.iterator(); i.hasNext();){
            VinylObject vinylObject = (VinylObject)i.next();
            AlbumDetailsObject album2 = vinylObject.getAlbumDet();

            if(album1.getArtist()!=album2.getArtist())
                continue;
            if(album1.getAlbum()!=album2.getAlbum())
                continue;
            searchResults.add(vinylObject);
        }
        return searchResults;
    }

    public void sortAlbums(SortingCriteria sortingCriteria){
        //sort linked list;

        Collections.sort(vinylObjects,new Comparator<VinylObject>() {
            @Override
            public int compare(VinylObject o1, VinylObject o2) {
                String temp1;
                String temp2;
                switch (sortingCriteria) {
                    case Artist:
                        temp1 = o1.getAlbumDet().getArtist();
                        temp2 = o2.getAlbumDet().getArtist();
                        break;
                    case Album:
                        temp1 = o1.getAlbumDet().getAlbum();
                        temp2 = o2.getAlbumDet().getAlbum();
                        break;
                    default:
                        temp1 = o1.getAlbumDet().getArtist();
                        temp2 = o2.getAlbumDet().getArtist();
                }
                if (temp1.charAt(0) > temp2.charAt(0)) {
                    return 1;
                }
                else
                    return 0;
            }
        });
    }
    public void deleteAlbums(VinylObject vinylObjectToDelete){
        AlbumDetailsObject album1  = vinylObjectToDelete.getAlbumDet();
        int nextIndex =0;
        List<Integer> indices = new LinkedList <Integer>();
        for (Iterator i = vinylObjects.iterator(); i.hasNext();){
            VinylObject vinylObject = (VinylObject)i.next();
            AlbumDetailsObject album2 = vinylObject.getAlbumDet();

            if(album1.getAlbum().equals(album2.getAlbum())){
                indices.add(nextIndex);
            }
            nextIndex +=1;
        }
        for(int i=indices.size()-1; i>=0;i-- ){
            nextIndex = indices.get(i);
            vinylObjects.remove(nextIndex);
        }
        System.out.println("Successfully deleted "+indices.size()+" vinyls");
        saveVinyls();
    }
    public String[] readAlbums(){
        Set<String> noDuplicates = vinylObjects
                .stream()
                .map((VinylObject vinylObject1) -> vinylObject1.getAlbumDet().getAlbum())
                .collect(Collectors.toSet());
        List<String> sortedArray = noDuplicates.stream().sorted().collect(Collectors.toList());


        int length = sortedArray.size()+1;
        int nextIndex = 1;

        String[] albums = new String[length];

        for (String vinyl :sortedArray){
            albums[nextIndex] = vinyl;
            nextIndex++;
        }

        albums[0] = "Album";

        return 	albums;
    }
}
