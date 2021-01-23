package com.ensonglopedia.dao;

import com.ensonglopedia.entities.SongObject;

import java.util.List;

public interface SongDao {
    void loadSongs(List<SongObject> songObjects);
    void saveSongs(List<SongObject> songObjects);
}
