package com.ensonglopedia.dao;

import com.ensonglopedia.entities.VinylObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

public class VinylDaoImpl implements VinylDao{

    private String directory = "vinylsList.csv";
    private String basedirectory = new File(".").getAbsoluteFile().toString();

    public void loadVinyls(List<VinylObject> vinylObjects)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(basedirectory.replace(".","")+directory));

            String sReadLine = reader.readLine();
            while (sReadLine!= null)
            {
                String[] stringArray = sReadLine.split(",");
                VinylObject vinylObject = new VinylObject();

                vinylObject.setReleaseDate(stringArray[0]);
                vinylObject.getAlbumDet().setArtist(stringArray[1]);
                vinylObject.getAlbumDet().setAlbum(stringArray[2]);

                vinylObjects.add(vinylObject);
                sReadLine = reader.readLine();
            }
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println("Vinyl list does not exist");
            //this outputs the message to tell the user that something has gone wrong
        }
    }

    public void saveVinyls(List<VinylObject> vinylObjects){
        try //attempt this method
        {
            BufferedWriter bw=new BufferedWriter(new FileWriter(basedirectory.replace(".","")+directory));

            for (Iterator i = vinylObjects.iterator(); i.hasNext();){
                VinylObject vinylObject = (VinylObject)i.next();
                bw.write(vinylObject.toString());
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
