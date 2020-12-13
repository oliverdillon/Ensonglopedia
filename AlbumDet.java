package OJDevs.ensonglopediaPack;
public class AlbumDet{
	private String artist, MusicBook;
	
	public AlbumDet (){
		this.artist = "";
		this.MusicBook = "";
	}
	public AlbumDet (String artIn,String refIn){
		this.artist = artIn;
		this.MusicBook = refIn;
	}

	public String getArtist(){
		return artist;
	}	
	public String getMusicBook(){
		return MusicBook;
	}
	public void setArtist(String artist){
		this.artist = artist;
	}	
	public void setMusicBook(String MusicBook){
		this.MusicBook = MusicBook;
	}
}
	