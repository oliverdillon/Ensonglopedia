package com.ensonglopedia.view;

import com.ensonglopedia.repository.SongBookRepository;
import com.ensonglopedia.service.ApplicationService;
import com.ensonglopedia.view.factories.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlbumInputView extends AbstractInputView {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private SongBookRepository songBookRepository;

    private long secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    private long milliTime = ZonedDateTime.now().toInstant().toEpochMilli();


    private JButton addAlbumbttn = new JButton();
    private JButton deleteAlbumbttn = new JButton();

    private	JLabel titleLabel;

    private JPanel mainPanel;
    private	JPanel sArtistBorderPanel;
    private JPanel sAlbumBorderPanel;
    private JPanel sDateBorderPanel;

    private JTextField sArtisttxt = new JTextField();
    private JTextField sAlbumtxt = new JTextField();
    private JComboBox sYearCombo = new JComboBox();


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
                        60,100,this);
        mainPanel.add(sArtistBorderPanel);

        //Select Album
        String[] albums = songBookRepository.readAlbums();

        sAlbumBorderPanel= FormatTextBoxFactory
                .createTextBox (sAlbumtxt,
                        "Album",
                        420,100,this);
        mainPanel.add(sAlbumBorderPanel);

        //Button
        addAlbumbttn = FormatButtonFactory
                .createButton (addAlbumbttn,"Add Album",
                        "Click me to add a album",
                        510,220,this);
        mainPanel.add(addAlbumbttn);


        String[] years = new String[101];
        Calendar calendar = new GregorianCalendar();
        years[0]="YYYY                           ";
        int currentYear = calendar.getInstance().get(calendar.YEAR);
        for (int i=1;i<years.length;i++){
            int num = currentYear-100+i;
            years[i]=""+num;
        }
        sDateBorderPanel = FormattedComboBoxFactory
                .createComboBox (sYearCombo, "Release Date",
                        years,780,100,this);
        mainPanel.add(sDateBorderPanel);

        return mainPanel;
    }
    public void inputInfo (){
        String artist = sArtisttxt.getText();
        String album = sAlbumtxt.getText();
        String releaseYear = (String)sYearCombo.getSelectedItem();

        if(artist.equals("Artist")||artist.equals("")){
            JOptionPane.showMessageDialog(mainPanel, "Please enter a Artist");
        }
        else if(album.equals("Album")||album.equals("")){
            JOptionPane.showMessageDialog(mainPanel, "Please enter a Album");
        }
        else if(releaseYear.equals("YYYY                           ")){
            JOptionPane.showMessageDialog(mainPanel, "Please enter a release year");
        }
        else
        {
            applicationService.addVinylAlbum(artist,album,releaseYear);
            sArtisttxt.setText("Artist");
            sAlbumtxt.setText("Album");
            sYearCombo.setSelectedItem("YYYY                           ");
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
                    sYearCombo.requestFocus();
                }
                if(e.getSource() == sYearCombo)
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
