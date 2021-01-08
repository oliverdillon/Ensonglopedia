package com.ensonglopedia.entities;

public class AlbumDetailsObject {
    private String artist, Album;

    public AlbumDetailsObject(){
        this.artist = "";
        this.Album = "";
    }
    public AlbumDetailsObject(String artIn, String refIn){
        this.artist = artIn;
        this.Album = refIn;
    }

    public String getArtist(){
        return artist;
    }
    public String getAlbum(){
        return Album;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }
    public void setAlbum(String Album){
        this.Album = Album;
    }
}
