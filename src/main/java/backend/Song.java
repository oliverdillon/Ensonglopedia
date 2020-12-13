package backend;

public class Song {
    private String title;
    private AlbumDet album;

    public Song (){
        this.title = "";
        this.album = new AlbumDet();
    }
    public Song (String titleIn,String artIn,String refIn){
        this.title = titleIn;
        this.album = new AlbumDet(artIn,refIn);
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;

    }
    public AlbumDet getAlbumDet(){
        return album;
    }
    public String toString()
    {
        return this.title+","+this.album.getArtist()+","+this.album.getMusicBook();
    }
}
