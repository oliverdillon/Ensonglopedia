package com.ensonglopedia.entities;

public class SongObject {
    private String title;
    private MusicBookDetailsObject album;

    public SongObject(){
        this.title = "";
        this.album = new MusicBookDetailsObject();
    }
    public SongObject(String titleIn, String artIn, String refIn){
        this.title = titleIn;
        this.album = new MusicBookDetailsObject(artIn,refIn);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;

    }
    public MusicBookDetailsObject getAlbumDet(){
        return album;
    }
    public String toString()
    {
        return this.title+","+this.album.getArtist()+","+this.album.getMusicBook();
    }
}
