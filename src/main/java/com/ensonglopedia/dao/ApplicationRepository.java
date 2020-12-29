package com.ensonglopedia.dao;

import com.ensonglopedia.entities.MusicBookDetailsObject;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.SortingCriteria;

import java.util.List;

public interface ApplicationRepository {
    void loadSongs();
    List<SongObject> getSongObjects();
    void addSong(String Title,String Artist,String MusicBook);
    SongObject searchSongs(SongObject songObjectForSearch);
    void saveClass();
    List <SongObject> searchAlbums(MusicBookDetailsObject album1);
    void sortMusicBooks(SortingCriteria sortingCriteria);
    void deleteBooks(SongObject songObjectToDelete);
    String[] readMusicBooks();
}
