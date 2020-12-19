package com.ensonglopedia.view;

import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.ApplicationService;
import com.ensonglopedia.view.factories.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

public class WindowView extends JFrame implements ActionListener, FocusListener, KeyListener {
    private JPanel mainPanel;
    private	JLabel titleLabel;
    private	JPanel sTitleInputBorderPanel;
    private	JPanel sArtistBorderPanel;
    private	JPanel sMusicBookBorderPanel;
    private JTextField sTitleInputtxt;
    private JTextField sArtisttxt;
    private JTextField sMusicBooktxt;
    private JComboBox<String> sMusicBookCombo;
    private long secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    private long milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
    private ApplicationService EN = new ApplicationService();
    private JFrame mainWindow;
    private JButton addBookbttn;
    private JButton deleteBookbttn;

    public WindowView(){
        this.createPanel();
    }

    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    private void refreshCombobox(){
        mainPanel.remove(sMusicBookCombo);
        String[] musicBooks = EN.readMusicBooks();
        sMusicBookCombo = new JComboBox<String>(musicBooks);
        sMusicBookCombo= FormattedComboBoxFactory.createComboBox(sMusicBookCombo,80,220,this);
        mainPanel.add(sMusicBookCombo);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
    public JPanel createPanel() //creates the Panel
    {

        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        //Components
        titleLabel = new JLabel();

        sTitleInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sArtistBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sMusicBookBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        sTitleInputtxt = new JTextField();//createFormatter("U***********************")
        sArtisttxt = new JTextField();
        sMusicBooktxt = new JTextField();

        //Button
        addBookbttn = new JButton();
        deleteBookbttn = new JButton();
        String[] musicBooks = EN.readMusicBooks();
        sMusicBookCombo = new JComboBox<String>(musicBooks);

        //Window Title
        titleLabel= FormattedTextLabelFactory.createTextLabel ("Ensonglopedia",JLabel.CENTER,JLabel.CENTER);
        mainPanel.add(titleLabel);

        //Title
        sTitleInputBorderPanel= FormattedTextBoxFactory.createTextBox (sTitleInputtxt,"Title",80,100,this,this);
        mainPanel.add(sTitleInputBorderPanel);

        //Artist
        sArtistBorderPanel= FormattedTextBoxFactory.createTextBox (sArtisttxt,"Artist",440,100,this,this);
        mainPanel.add(sArtistBorderPanel);

        //MusicBook
        //sMusicBookBorderPanel= createTextBox (sMusicBooktxt,"MusicBook",800,100);
        //mainPanel.add(sMusicBookBorderPanel);

        //Select Music Book
        sMusicBookCombo= FormattedComboBoxFactory.createComboBox (sMusicBookCombo,80,220,this);
        mainPanel.add(sMusicBookCombo);

        //Button
        addBookbttn= FormattedButtonFactory.createButton (addBookbttn,"Add Music Book","Click me to add a music book",440,220,this);
        mainPanel.add(addBookbttn);

        //Delete a music book Button
        deleteBookbttn= FormattedButtonFactory.createButton (deleteBookbttn,"Delete Music Book","Click me to delete a music book",620,220,this);
        mainPanel.add(deleteBookbttn);

        return mainPanel;
    }
    public void actionPerformed(ActionEvent e) //The Action Listener (performs tasks that are connected to each button)
    {
        if(ZonedDateTime.now().toInstant().getEpochSecond()-secondTime>1){
            //------------------Add Book------------------//
            if(e.getSource() == addBookbttn)
            {
                File file = new File("Music_book");
                String path = file.getAbsolutePath();
                BufferedImage icon;

                try
                {
                    icon = ImageIO.read(new File(path+".jpg"));
                    //mainWindow.setIconImage();
                }
                catch(IOException io)
                {
                    System.out.println("Error Loading Icon");
                }

                boolean repeat = true;
                while (repeat==true){
                    String MusicBook = JOptionPane.showInputDialog(mainWindow,"Please enter the name of book:",
                            "Add Book",JOptionPane.PLAIN_MESSAGE);

                    //If a string was returned, say so.
                    if ((MusicBook != null) && (MusicBook.length() > 0)) {
                        EN.addSong("","",MusicBook);
                        repeat=false;
                    }
                    else{
                        JOptionPane.showMessageDialog(mainWindow,"Invalid input");
                    }
                }
                refreshCombobox();

            }
            if(e.getSource() == deleteBookbttn)
            {
                String MusicBook = sMusicBookCombo.getSelectedItem().toString();
                SongObject songObject = new SongObject("","",MusicBook);
                EN.deleteBooks(songObject);
                refreshCombobox();
            }
        }
        secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
    }
    public JFrame addAllComponents(JFrame mainWindow){
        this.mainWindow = mainWindow;
        mainWindow.add(mainPanel);

        return mainWindow;
    }

    ///================================================///
    ///KEY LISTENERS///
    ///================================================///
    public void keyTyped(KeyEvent e){
        if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
            if(e.getKeyChar() == KeyEvent.VK_ENTER)
            {
                if(e.getSource() == sTitleInputtxt)
                    sArtisttxt.requestFocus();
                if(e.getSource() == sArtisttxt){
                    sTitleInputtxt.requestFocus();
                    String title = sTitleInputtxt.getText();
                    String artist = sArtisttxt.getText();
                    String MusicBook = sMusicBookCombo.getSelectedItem().toString();


                    if(title.equals("Title")){
                        JOptionPane.showMessageDialog(mainWindow, "Please enter a Title");
                    }
                    else if(artist.equals("Artist")||artist.equals("")){
                        JOptionPane.showMessageDialog(mainWindow, "Please enter a Artist");
                    }
                    else if(MusicBook.equals("Select Music Book")){
                        JOptionPane.showMessageDialog(mainWindow, "Please select a Music Book");
                    }
                    else
                    {
						/*
						MusicBook ref;
						try{
							ref = MusicBook.valueOf(MusicBook);
						}
						catch(Exception ex){
							ref =MusicBook.Book2;
							System.out.println("Unknown MusicBook");
						}
						*/
                        EN.addSong(title,artist,MusicBook);

                        sTitleInputtxt.setText("Title");
                        sArtisttxt.setText("Artist");
                    }
                }
            }
        }
        milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
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
