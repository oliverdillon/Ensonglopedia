import GUI

public class ensonglopedia
{
	private List Songs;
	
	public ensonglopedia(){
		
		Songs = new LinkedList();
	}
	public void addSong(String Title,String Artist,String Reference){
		Song song = new Song(Title,Artist,Reference);
		Songs.add(song);
		
	}
}

public enum Refererence{
	Book1, Book2, Book3
	
	public String toString(){
		switch(this){
			case Book1:
				return "Example Book"
		}
	}
}

public class Song{
	private String Title, Artist, Reference
	private double length
	
	public Song (String Title,String Artist,Refererence Reference){
		this.Title = Title
		this.Artist = Artist
		this.Reference = Reference
	}
	public String getTitle(){
		return Title;
		
	}
	public String getArtist(){
		return Artist;
	}	
	public String getReference(){
		return Reference;
	}
	public void setTitle(String Title){
		this.Title = Title
	}
	public void setArtist(String Artist){
		this.Artist = Artist
	}	
	public void setReference(String Reference){
		this.Reference = Reference
	}
}


public class FlyTest{
	GUI FirstGUi = new GUI();
	
	
}