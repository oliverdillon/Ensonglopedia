package com.ensonglopedia.dao;

import com.ensonglopedia.entities.VinylObject;

import java.util.List;

public interface VinylDao {
    void loadVinyls(List<VinylObject> vinylObjects);
    void saveVinyls(List<VinylObject> vinylObjects);
}
