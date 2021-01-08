package com.ensonglopedia.service;

import com.ensonglopedia.entities.SongObject;

public interface ApplicationService {
    void addSong(String Title,String Artist,String MusicBook);
    void deleteBooks(SongObject songObjectToDelete);
    String[] readMusicBooks();
}
