package com.ensonglopedia.view;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.ApplicationService;
import com.ensonglopedia.view.factories.FormattedColorsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StorageView extends JFrame implements MouseListener, KeyListener {

    @Autowired
    private ApplicationRepository applicationRepository;

    private JPanel mainPanel;
    private JLabel titleLabel;

    public JPanel createPanel() //creates the Panel
    {
        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        String[] columnNames = {"Song","Artist","Music Book"};
        int size =applicationRepository.getSongObjects().size();
        Object[][] data = new Object[size][3];
        int  i=0;
        for (SongObject s : applicationRepository.getSongObjects()) {
            data[i][0] = s.getTitle();
            data[i][1] = s.getAlbumDet().getArtist();
            data[i][2] = s.getAlbumDet().getMusicBook();
            i++;
        }
        JTable songTable =new JTable(data,columnNames);
        songTable.setGridColor(Color.white);
        songTable.getTableHeader().setBackground(Color.white);

        //uses the member's personal info and the headings to create the members table
        JScrollPane tablePane = new JScrollPane(songTable); //creates a new scroll pane containing the members table
        tablePane.setLocation(90,60);
        tablePane.setSize(700,210);
        songTable.addMouseListener(this);
        songTable.addKeyListener(this);
        mainPanel.add(tablePane); // adds the member pane the table panel
        tablePane.setVisible(true);

        //Window Title
        //titleLabel= FormattedTextLabelFactory.createTextLabel ("Ensonglopedia",JLabel.CENTER,JLabel.CENTER);
        //mainPanel.add(titleLabel);


        return mainPanel;
    }
    ///================================================///
    ///KEY LISTENERS///
    ///================================================///
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    ///================================================///
    ///MOUSE LISTENERS///
    ///================================================///
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}
