package com.ensonglopedia.service;

import com.ensonglopedia.entities.SongObject;

public interface ApplicationService {
    void readRepositories();
    void saveRepositories();
    void addSong(String Title,String Artist,String Album);
    void addVinylAlbum(String ReleaseDate, String Artist, String Album);
    void deleteAlbums(SongObject songObjectToDelete);
    String[] readAlbums();
}
