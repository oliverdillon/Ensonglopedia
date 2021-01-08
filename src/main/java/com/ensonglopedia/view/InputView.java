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
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.ZonedDateTime;

public class InputView extends JFrame implements ActionListener, FocusListener, KeyListener {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    private long secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    private long milliTime = ZonedDateTime.now().toInstant().toEpochMilli();


    private JButton addBookbttn = new JButton();
    private JButton deleteBookbttn = new JButton();

    private	JLabel titleLabel;

    private JPanel mainPanel;
    private	JPanel sTitleInputBorderPanel;
    private	JPanel sArtistBorderPanel;
    private JPanel sMusicBookBorderPanel;

    private JTextField sTitleInputtxt= new JTextField();;
    private JTextField sArtisttxt = new JTextField();;
    private JTextField sMusicBooktxt = new JTextField();;

    private JComboBox<String> sMusicBookCombo = new JComboBox<>();

    public JPanel createPanel() //creates the Panel
    {

        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        //Window Title
        titleLabel= FormattedTextLabelFactory.createTextLabel ("Ensonglopedia",JLabel.CENTER,JLabel.CENTER);
        mainPanel.add(titleLabel);

        //Title
        sTitleInputBorderPanel= FormatTextBoxFactory.createTextBox (sTitleInputtxt,"Title",80,100,this);
        mainPanel.add(sTitleInputBorderPanel);

        //Artist
        sArtistBorderPanel= FormatTextBoxFactory.createTextBox (sArtisttxt,"Artist",440,100,this);
        mainPanel.add(sArtistBorderPanel);

        //Select Music Book
        String[] musicBooks = applicationRepository.readMusicBooks();

        sMusicBookBorderPanel= FormattedComboBoxFactory.createComboBox (sMusicBookCombo, "Album",musicBooks,800,100,this);
        mainPanel.add(sMusicBookBorderPanel);

        //Button
        addBookbttn= FormatButtonFactory.createButton (addBookbttn,"Add Music Book","Click me to add a music book",440,220,this);
        mainPanel.add(addBookbttn);

        //Delete a music book Button
        deleteBookbttn= FormatButtonFactory.createButton (deleteBookbttn,"Delete Music Book","Click me to delete a music book",620,220,this);
        mainPanel.add(deleteBookbttn);

        return mainPanel;
    }
    public void actionPerformed(ActionEvent e) //The Action Listener (performs tasks that are connected to each button)
    {
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            //------------------Add Book------------------//
            if(e.getSource() == addBookbttn)
            {
                boolean repeat = true;
                while (repeat==true){
                    String MusicBook = JOptionPane.showInputDialog(mainPanel,"Please enter the name of book:",
                            "Add Book",JOptionPane.PLAIN_MESSAGE);

                    //If a string was returned, say so.
                    if ((MusicBook != null)) {
                        applicationService.addSong("","",MusicBook);
                        repeat=false;
                    }
                    else{
                        JOptionPane.showMessageDialog(mainPanel,"Invalid input");
                    }
                }
                String[] musicBooks = applicationService.readMusicBooks();
                FormattedComboBoxFactory.refreshComboBox(mainPanel,sMusicBookBorderPanel,sMusicBookCombo,"Album",musicBooks,this);

            }
            //------------------Delete Book------------------//
            if(e.getSource() == deleteBookbttn)
            {
                System.out.println("out");
                String MusicBook = sMusicBookCombo.getSelectedItem().toString();
                SongObject songObject = new SongObject("","",MusicBook);
                applicationService.deleteBooks(songObject);
                String[] musicBooks = applicationService.readMusicBooks();
                FormattedComboBoxFactory.refreshComboBox(mainPanel,sMusicBookBorderPanel,sMusicBookCombo,"Album",musicBooks,this);
            }
        }
        secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    }

    ///================================================///
    ///KEY LISTENERS///
    ///================================================///
    public void keyReleased(KeyEvent e){
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            if(e.getKeyChar() == KeyEvent.VK_ENTER)
            {
                if(e.getSource() == sTitleInputtxt)
                    sArtisttxt.requestFocus();
                if(e.getSource() == sArtisttxt)
                    sMusicBookCombo.requestFocus();
                if(e.getSource() == sMusicBookCombo)
                {
                    sTitleInputtxt.requestFocus();
                    String title = sTitleInputtxt.getText();
                    String artist = sArtisttxt.getText();
                    String MusicBook = sMusicBookCombo.getSelectedItem().toString();


                    if(title.equals("Title")){
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a Title");
                    }
                    else if(artist.equals("Artist")||artist.equals("")){
                        JOptionPane.showMessageDialog(mainPanel, "Please enter a Artist");
                    }
                    else if(MusicBook.equals("Select Music Book")){
                        JOptionPane.showMessageDialog(mainPanel, "Please select a Music Book");
                    }
                    else
                    {
                        applicationService.addSong(title,artist,MusicBook);

                        sTitleInputtxt.setText("Title");
                        sArtisttxt.setText("Artist");
                    }
                }
            }
        }
        milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    public void keyPressed(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    ///================================================///
    ///FOCUS LISTENERS///
    ///================================================///
    public void focusGained(FocusEvent fe){
        if(fe.getSource() == sTitleInputtxt){
            if(sTitleInputtxt.getText().equals("Title"))
                sTitleInputtxt.setText("");
        }
        if(fe.getSource() == sArtisttxt){
            if(sArtisttxt.getText().equals("Artist"))
                sArtisttxt.setText("");
        }
        if(fe.getSource() == sMusicBooktxt){
            if(sMusicBooktxt.getText().equals("MusicBook"))
                sMusicBooktxt.setText("");
        }
    }
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
        if(fe.getSource() == sMusicBooktxt){
            if(sMusicBooktxt.getText().equals("")){
                sMusicBooktxt.setText("MusicBook");
            }
        }
    }
}
