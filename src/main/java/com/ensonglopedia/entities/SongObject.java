package com.ensonglopedia.entities;

public class SongObject {
    private String title;
    private AlbumDetailsObject album;

    public SongObject(){
        this.title = "";
        this.album = new AlbumDetailsObject();
    }
    public SongObject(String titleIn, String artIn, String refIn){
        this.title = titleIn;
        this.album = new AlbumDetailsObject(artIn,refIn);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;

    }
    public AlbumDetailsObject getAlbumDet(){
        return album;
    }
    public String toString()
    {
        return this.title+","+this.album.getArtist()+","+this.album.getAlbum();
    }
}
