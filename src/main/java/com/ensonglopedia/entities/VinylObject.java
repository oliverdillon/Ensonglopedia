package com.ensonglopedia.entities;

public class VinylObject {
    private String releaseDate;
    private AlbumDetailsObject album;

    public VinylObject(){
        this.releaseDate = "";
        this.album = new AlbumDetailsObject();
    }
    public VinylObject(String Artist, String Album,String date){
        this.album = new AlbumDetailsObject(Artist,Album);
        this.releaseDate = date;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public String getReleaseDate(){
        return releaseDate;

    }
    public AlbumDetailsObject getAlbumDet(){
        return album;
    }
    public String toString()
    {
        return this.album.getArtist()+","+this.album.getAlbum()+","+this.releaseDate;
    }

}
