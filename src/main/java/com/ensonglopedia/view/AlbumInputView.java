package com.ensonglopedia.view;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.service.ApplicationService;
import com.ensonglopedia.view.factories.FormatButtonFactory;
import com.ensonglopedia.view.factories.FormatTextBoxFactory;
import com.ensonglopedia.view.factories.FormattedColorsFactory;
import com.ensonglopedia.view.factories.FormattedTextLabelFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.time.ZonedDateTime;

public class AlbumInputView extends AbstractInputView {

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
    private	JPanel sArtistBorderPanel;
    private JPanel sAlbumBorderPanel;

    private JTextField sArtisttxt = new JTextField();
    private JTextField sAlbumtxt = new JTextField();


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

        //Artist
        sArtistBorderPanel= FormatTextBoxFactory
                .createTextBox (sArtisttxt,"Artist",
                        240,100,this);
        mainPanel.add(sArtistBorderPanel);

        //Select Album
        String[] albums = applicationRepository.readAlbums();

        sAlbumBorderPanel= FormatTextBoxFactory
                .createTextBox (sAlbumtxt,
                        "Album",
                        600,100,this);
        mainPanel.add(sAlbumBorderPanel);

        //Button
        addAlbumbttn = FormatButtonFactory
                .createButton (addAlbumbttn,"Add Album",
                        "Click me to add a album",
                        510,220,this);
        mainPanel.add(addAlbumbttn);

        return mainPanel;
    }
    public void inputInfo (){
        String artist = sArtisttxt.getText();
        String album = sAlbumtxt.getText();

        if(artist.equals("Artist")||artist.equals("")){
            JOptionPane.showMessageDialog(mainPanel, "Please enter a Artist");
        }
        else if(album.equals("Album")||album.equals("")){
            JOptionPane.showMessageDialog(mainPanel, "Please enter a Album");
        }
        else
        {
            applicationService.addSong("",artist,album);
            sArtisttxt.setText("Artist");
            sAlbumtxt.setText("Album");
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) //The Action Listener (performs tasks that are connected to each button)
    {
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            //------------------Add Album------------------//
            if(e.getSource() == addAlbumbttn)
            {
                inputInfo();
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
                if(e.getSource() == sArtisttxt)
                    sAlbumtxt.requestFocus();
                if(e.getSource() == sAlbumtxt)
                {
                    sArtisttxt.requestFocus();
                    inputInfo();
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
        if(fe.getSource() == sArtisttxt){
            if(sArtisttxt.getText().equals("Artist"))
                sArtisttxt.setText("");
        }
        if(fe.getSource() == sAlbumtxt){
            if(sAlbumtxt.getText().equals("Album"))
                sAlbumtxt.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent fe){
        if(fe.getSource() == sArtisttxt){
            if(sArtisttxt.getText().equals("")){
                sArtisttxt.setText("Artist");
            }
        }
        if(fe.getSource() == sAlbumtxt){
            if(sAlbumtxt.getText().equals("")){
                sAlbumtxt.setText("Album");
            }
        }
    }
}
