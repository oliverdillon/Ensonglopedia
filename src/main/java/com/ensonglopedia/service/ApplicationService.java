package com.ensonglopedia.service;

import com.ensonglopedia.entities.SongObject;

import java.util.List;

public interface ApplicationService {
    List<SongObject> getSongObjects();
    void addSong(String Title,String Artist,String MusicBook);
    void deleteBooks(SongObject songObjectToDelete);
    String[] readMusicBooks();

}
