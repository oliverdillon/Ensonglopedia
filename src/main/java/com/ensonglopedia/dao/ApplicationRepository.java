package com.ensonglopedia.dao;

import com.ensonglopedia.entities.AlbumDetailsObject;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.SortingCriteria;

import java.util.List;

public interface ApplicationRepository {
    void loadSongs();
    List<SongObject> getSongObjects();
    void addSong(String Title,String Artist,String Album);
    SongObject searchSongs(SongObject songObjectForSearch);
    void saveClass();
    List <SongObject> searchAlbums(AlbumDetailsObject album1);
    void sortAlbums(SortingCriteria sortingCriteria);
    void deleteAlbums(SongObject songObjectToDelete);
    String[] readAlbums();
}
