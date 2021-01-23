package com.ensonglopedia.service;

import com.ensonglopedia.repository.SongBookRepository;
import com.ensonglopedia.entities.AlbumDetailsObject;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.repository.VinylRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    SongBookRepository songBookRepository;

    @Autowired
    VinylRepository vinylRepository;

    public ApplicationServiceImpl(){
    }
    public void readRepositories(){
        songBookRepository.loadSongs();
        vinylRepository.loadVinyls();
    }

    public void saveRepositories(){
        songBookRepository.saveSongs();
        vinylRepository.saveVinyls();
    }
    public List<SongObject> getSongObjects() {
        return songBookRepository.getSongObjects();
    }

    public void loadSongs() {
        songBookRepository.loadSongs();
    }

    public void addSong(String Title, String Artist, String Album) {
        songBookRepository.addSong(Title, Artist, Album);
    }

    public void addVinylAlbum(String Artist, String Album,String ReleaseDate) {
        vinylRepository.addVinyl(Artist, Album,ReleaseDate);
    }

    public void printSong(SongObject songObject){
        System.out.println(songObject.getTitle()+"	"+ songObject.getAlbumDet().getArtist()+"	"+ songObject.getAlbumDet().getAlbum());
    }

    public SongObject searchSongs(SongObject songObjectForSearch) {
        return songBookRepository.searchSongs(songObjectForSearch);
    }

    public void saveClass() {
        songBookRepository.saveSongs();
    }

    public List<SongObject> searchAlbums(AlbumDetailsObject album1) {
        return songBookRepository.searchAlbums(album1);
    }


    public void deleteAlbums(SongObject songObjectToDelete) {
        songBookRepository.deleteAlbums(songObjectToDelete);
    }

    public String[] readAlbums() {
        return songBookRepository.readAlbums();
    }
}
