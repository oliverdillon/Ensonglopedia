package com.ensonglopedia.view;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.ApplicationService;

import com.ensonglopedia.view.factories.FormatButtonFactory;
import com.ensonglopedia.view.factories.FormattedColorsFactory;
import com.ensonglopedia.view.factories.FormattedComboBoxFactory;
import com.ensonglopedia.view.factories.FormatTextBoxFactory;
import com.ensonglopedia.view.factories.FormattedTextLabelFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.time.ZonedDateTime;

public class SongInputView extends AbstractInputView  {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    private long secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    private long milliTime = ZonedDateTime.now().toInstant().toEpochMilli();


    private JButton addAlbumbttn = new JButton();
    private JButton deleteAlbumbttn = new JButton();

    private	JLabel titleLabel;

    private JPanel mainPanel;
    private	JPanel sTitleInputBorderPanel;
    private	JPanel sArtistBorderPanel;
    private JPanel sAlbumBorderPanel;

    private JTextField sTitleInputtxt= new JTextField();
    private JTextField sArtisttxt = new JTextField();

    private JComboBox<String> sAlbumCombo = new JComboBox<>();

    public JPanel createPanel() //creates the Panel
    {

        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        //Window Title
        titleLabel= FormattedTextLabelFactory
                .createTextLabel ("Ensonglopedia",JLabel.CENTER,JLabel.CENTER);
        mainPanel.add(titleLabel);

        //Title
        sTitleInputBorderPanel= FormatTextBoxFactory
                .createTextBox (sTitleInputtxt,"Title",
                        80,100,this);
        mainPanel.add(sTitleInputBorderPanel);

        //Artist
        sArtistBorderPanel= FormatTextBoxFactory
                .createTextBox (sArtisttxt,"Artist",
                        440,100,this);
        mainPanel.add(sArtistBorderPanel);

        //Select Album
        String[] albums = applicationRepository.readAlbums();

        sAlbumBorderPanel= FormattedComboBoxFactory
                .createComboBox (sAlbumCombo, "Album",
                        albums,800,100,this);
        mainPanel.add(sAlbumBorderPanel);

        //Button
        addAlbumbttn= FormatButtonFactory
                .createButton (addAlbumbttn,"Add Album",
                        "Click me to add a album",
                        440,220,this);
        mainPanel.add(addAlbumbttn);

        //Delete a album Button
        deleteAlbumbttn= FormatButtonFactory
                .createButton (deleteAlbumbttn,"Delete Album",
                        "Click me to delete a album",
                        620,220,this);
        mainPanel.add(deleteAlbumbttn);

        return mainPanel;
    }
    public void refreshCombobox(){
        String[] albums = applicationService.readAlbums();
        FormattedComboBoxFactory.refreshComboBox(mainPanel,sAlbumBorderPanel,sAlbumCombo,"Album",albums,this);
    }
    @Override
    public void actionPerformed(ActionEvent e) //The Action Listener (performs tasks that are connected to each button)
    {
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            //------------------Add Album------------------//
            if(e.getSource() == addAlbumbttn)
            {
                boolean repeat = true;
                while (repeat==true){
                    String Album = JOptionPane.showInputDialog(mainPanel,"Please enter the name of album:",
                            "Add Album",JOptionPane.PLAIN_MESSAGE);

                    //If a string was returned, say so.
                    if ((Album != null)) {
                        applicationService.addSong("","",Album);
                        repeat=false;
                    }
                    else{
                        JOptionPane.showMessageDialog(mainPanel,"Invalid input");
                    }
                }
                refreshCombobox();
            }
            //------------------Delete Album------------------//
            if(e.getSource() == deleteAlbumbttn)
            {
                System.out.println("out");
                String Album = sAlbumCombo.getSelectedItem().toString();
                SongObject songObject = new SongObject("","",Album);
                applicationService.deleteAlbums(songObject);
                refreshCombobox();
            }
        }
        secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    }

    ///================================================///
    ///KEY LISTENERS///
    ///================================================///
    @Override
    public void keyReleased(KeyEvent e){
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            if(e.getKeyChar() == KeyEvent.VK_ENTER)
            {
                if(e.getSource() == sTitleInputtxt)
                    sArtisttxt.requestFocus();
                if(e.getSource() == sArtisttxt)
                    sAlbumCombo.requestFocus();
                if(e.getSource() == sAlbumCombo)
                {
                    sTitleInputtxt.requestFocus();
                    String title = sTitleInputtxt.getText();
                    String artist = sArtisttxt.getText();
                    String Album = sAlbumCombo.getSelectedItem().toString();


                    if(title.equals("Title")){
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a Title");
                    }
                    else if(artist.equals("Artist")||artist.equals("")){
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a Artist");
                    }
                    else if(Album.equals("Select Album")){
                        JOptionPane.showMessageDialog(mainPanel, "Please select a Album");
                    }
                    else
                    {
                        applicationService.addSong(title,artist,Album);

                        sTitleInputtxt.setText("Title");
                        sArtisttxt.setText("Artist");
                    }
                }
            }
        }
        milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    ///================================================///
    ///FOCUS LISTENERS///
    ///================================================///
    @Override
    public void focusGained(FocusEvent fe){
        if(fe.getSource() == sTitleInputtxt){
            if(sTitleInputtxt.getText().equals("Title"))
                sTitleInputtxt.setText("");
        }
        if(fe.getSource() == sArtisttxt){
            if(sArtisttxt.getText().equals("Artist"))
                sArtisttxt.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent fe){
        if(fe.getSource() == sTitleInputtxt){
            if(sTitleInputtxt.getText().equals("")){
                sTitleInputtxt.setText("Title");
            }
        }
        if(fe.getSource() == sArtisttxt){
            if(sArtisttxt.getText().equals("")){
                sArtisttxt.setText("Artist");
            }
        }
    }
}
