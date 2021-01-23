package com.ensonglopedia.repository;

import com.ensonglopedia.entities.AlbumDetailsObject;
import com.ensonglopedia.entities.VinylObject;
import com.ensonglopedia.service.SortingCriteria;

import java.util.List;

public interface VinylRepository {
    void loadVinyls();
    List<VinylObject> getVinylObjects();
    void addVinyl(String Artist,String Album,String ReleaseDate);
    VinylObject searchVinyls(VinylObject vinylObjectForSearch);
    void saveVinyls();
    List <VinylObject> searchAlbums(AlbumDetailsObject album1);
    void sortAlbums(SortingCriteria sortingCriteria);
    void deleteAlbums(VinylObject vinylObjectToDelete);
    String[] readAlbums();
}
