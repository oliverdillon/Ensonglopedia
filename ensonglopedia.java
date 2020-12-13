package OJDevs.ensonglopediaPack;
import OJDevs.ensonglopediaPack.*;
import java.io.*; 
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
public class ensonglopedia
{
	private List <Song> Songs;
	
	public ensonglopedia(){
		
		Songs = new LinkedList <Song>();
		this.loadSongs();
	}
	public void addSong(String Title,String Artist,String MusicBook){
		Song song = new Song(Title,Artist,MusicBook);
		Song songTemp = new Song("","",MusicBook);
		Song searchResults = searchSongs(songTemp);
		
		if(searchResults!=null){
			searchResults.setTitle(Title);
			searchResults.getAlbumDet().setArtist(Artist);
			System.out.println("check1");
		}
		else{
			Songs.add(song);
			System.out.println("check2");
		}
		saveClass();
	}
	public void printSong(Song song){
		System.out.println(song.getTitle()+"	"+song.getAlbumDet().getArtist()+"	"+song.getAlbumDet().getMusicBook());	
	}
	
	public Song searchSongs(Song songForSearch){
		
		AlbumDet album1  = songForSearch.getAlbumDet();
		
		for (Iterator i = Songs.iterator(); i.hasNext();){
				Song song = (Song)i.next();
				AlbumDet album2 = song.getAlbumDet();
				
				if(songForSearch.getTitle().equals(song.getTitle()))
					if(album1.getArtist().equals(album2.getArtist()))
						if(album1.getMusicBook().equals(album2.getMusicBook())){
							return song;
						}
		}
		return null;
	}
	public List <Song> searchAlbums(AlbumDet album1){
		List <Song> searchResults = new LinkedList <Song>();
		for (Iterator i = Songs.iterator(); i.hasNext();){
				Song song = (Song)i.next();
				AlbumDet album2 = song.getAlbumDet();
				
				if(album1.getArtist()!=album2.getArtist())
					continue;
				if(album1.getMusicBook()!=album2.getMusicBook())
					continue;
				searchResults.add(song);
		}
		return searchResults;
	}
	public void saveClass(){
		try //attempt this method
		{
			BufferedWriter bw=new BufferedWriter(new FileWriter("Ensonglopedia.csv"));
			for (Iterator i = Songs.iterator(); i.hasNext();){
				Song song = (Song)i.next();
				bw.write(song.toString());
				bw.newLine(); 
				//saves an array in the file
			}
			bw.close(); // closes the file
		}
		catch(Exception e) //If that did not work try this
		{
			System.out.println("Error when writing to file");
			//this outputs the message to tell the user that something has gone wrong

		}
		
	}
	public void sortMusicBooks(){
		//sort linked list;
	}
	public void deleteBooks(Song songToDelete){
		AlbumDet album1  = songToDelete.getAlbumDet();
		int nextIndex =0;
		List<Integer> indices = new LinkedList <Integer>();
		for (Iterator i = Songs.iterator(); i.hasNext();){
			Song song = (Song)i.next();
			AlbumDet album2 = song.getAlbumDet();
			
			if(album1.getMusicBook().equals(album2.getMusicBook())){
				indices.add(nextIndex);
			}
			nextIndex +=1;
		}
		for(int i=indices.size()-1; i>=0;i-- ){
			nextIndex = indices.get(i);
			System.out.println(nextIndex);
			Songs.remove(nextIndex);
		}
		
	}
	public int sortbystringArray(String[] musicBooks, int length)
	{
		int newLength =1;
		for(int i=0;i<length;i++)
			for(int k=0;k<length-1;k++){
				char[] booksChar1 = musicBooks[k].toCharArray();
				char[] booksChar2 = musicBooks[k+1].toCharArray();
				
				if(booksChar1[0]>booksChar2[0]) 
				{
					String temp = musicBooks[k];
					musicBooks [k] = musicBooks [k+1];//moves false record below the true one
					musicBooks [k+1] = temp;
				}
				
			}
		for(int i=0;i<length-1;i++)
			if(!musicBooks[i].equals(musicBooks[i+1])) 
			{
				newLength++;
			}
		return newLength;
	}
	public String[] readMusicBooks(){
		int length = Songs.size();
		int nextIndex1 = 0;
		
		String[] musicBooks = new String[length];
		
		for (Iterator i = Songs.iterator(); i.hasNext();){
			Song song = (Song)i.next();
			musicBooks[nextIndex1] = song.getAlbumDet().getMusicBook();
			nextIndex1++;
		}		
		
		int newLength = sortbystringArray(musicBooks,length);
		String[] newMusicBooks = new String[newLength+1];
		int nextIndex2 = 0;
		
		for(int i=0;i<length-1;i++){
			if(!musicBooks[i].equals(musicBooks[i+1])) 
			{
				newMusicBooks[nextIndex2] = musicBooks[i];
				nextIndex2++;
			}
		}
		newMusicBooks[nextIndex2] = musicBooks[length-1];
		nextIndex2++;		
		newMusicBooks[nextIndex2] = "Select Music Book";
		
		return 	newMusicBooks;
	}
	public void loadSongs()
	{
		try
		{			
			BufferedReader br=new BufferedReader(new FileReader("Ensonglopedia.csv")); //obtains text from specified file.
			String sReadLine = br.readLine();
			while (sReadLine!= null)
			{
				String[] stringArray = sReadLine.split(","); 
				Song tempSong = new Song();
				
				tempSong.setTitle(stringArray[0]);
				tempSong.getAlbumDet().setArtist(stringArray[1]);
				tempSong.getAlbumDet().setMusicBook(stringArray[2]);			
				Songs.add(tempSong);
				sReadLine = br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			System.out.println("Ensonglopedia does not exist");
			//this outputs the message to tell the user that something has gone wrong
		}
	}
}