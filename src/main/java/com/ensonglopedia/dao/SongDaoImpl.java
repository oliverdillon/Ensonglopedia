package com.ensonglopedia.dao;

import com.ensonglopedia.entities.SongObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

public class SongDaoImpl implements SongDao{

    private String directory = "songBookList.csv";
    private String basedirectory = new File(".").getAbsoluteFile().toString();

    public void loadSongs(List<SongObject> songObjects)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(basedirectory.replace(".","")+directory));

            String sReadLine = reader.readLine();
            while (sReadLine!= null)
            {
                String[] stringArray = sReadLine.split(",");
                SongObject tempSongObject = new SongObject();

                tempSongObject.setTitle(stringArray[0]);
                tempSongObject.getAlbumDet().setArtist(stringArray[1]);
                tempSongObject.getAlbumDet().setAlbum(stringArray[2]);

                songObjects.add(tempSongObject);
                sReadLine = reader.readLine();
            }
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Song book list does not exist");
            //this outputs the message to tell the user that something has gone wrong
        }
    }

    public void saveSongs(List<SongObject> songObjects){
        try //attempt this method
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(basedirectory.replace(".","")+directory));

            for (Iterator i = songObjects.iterator(); i.hasNext();){
                SongObject songObject = (SongObject)i.next();
                bw.write(songObject.toString());
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
}
