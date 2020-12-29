package com.ensonglopedia.service;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.entities.MusicBookDetailsObject;
import com.ensonglopedia.entities.SongObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(){
        System.out.println("Build Service");
    }

    public List<SongObject> getSongObjects() {
        return applicationRepository.getSongObjects();
    }

    public void loadSongs() {
        applicationRepository.loadSongs();
    }

    public void addSong(String Title, String Artist, String MusicBook) {
        applicationRepository.addSong(Title, Artist, MusicBook);

    }

    public void printSong(SongObject songObject){
        System.out.println(songObject.getTitle()+"	"+ songObject.getAlbumDet().getArtist()+"	"+ songObject.getAlbumDet().getMusicBook());
    }

    public SongObject searchSongs(SongObject songObjectForSearch) {
        return applicationRepository.searchSongs(songObjectForSearch);
    }

    public void saveClass() {
        applicationRepository.saveClass();
    }

    public List<SongObject> searchAlbums(MusicBookDetailsObject album1) {
        return applicationRepository.searchAlbums(album1);
    }


    public void deleteBooks(SongObject songObjectToDelete) {
        applicationRepository.deleteBooks(songObjectToDelete);

    }

    public String[] readMusicBooks() {
        return applicationRepository.readMusicBooks();
    }
}
