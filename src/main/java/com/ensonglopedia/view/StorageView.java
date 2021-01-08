package com.ensonglopedia.view;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.service.ApplicationService;
import com.ensonglopedia.view.factories.FormattedColorsFactory;
import com.ensonglopedia.view.factories.FormattedComboBoxFactory;
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
    private JScrollPane tablePane;

    public JPanel createPanel() //creates the Panel
    {
        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        tablePane = BuildTable();
        mainPanel.add(tablePane);

        return mainPanel;
    }
    public void refreshTable(){
        mainPanel.remove(tablePane);
        tablePane = BuildTable();
        mainPanel.add(tablePane);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
    public void refreshTable(JPanel mainPanel, JScrollPane tablePane){
        mainPanel.remove(tablePane);
        tablePane = BuildTable();
        mainPanel.add(tablePane);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
    public JScrollPane BuildTable (){

        int  i=0;
        int size =applicationRepository.getSongObjects().size();
        String[] columnNames = {"Song","Artist","Album"};
        Object[][] data = new Object[size][3];

        for (SongObject s : applicationRepository.getSongObjects()) {
            data[i][0] = s.getTitle();
            data[i][1] = s.getAlbumDet().getArtist();
            data[i][2] = s.getAlbumDet().getAlbum();
            i++;
        }

        JTable songTable =new JTable(data,columnNames);
        songTable.setGridColor(Color.white);
        songTable.getTableHeader().setBackground(Color.white);
        songTable.addMouseListener(this);
        songTable.addKeyListener(this);

        //uses the member's personal info and the headings to create the members table
        JScrollPane tablePane = new JScrollPane(songTable); //creates a new scroll pane containing the members table
        tablePane.setLocation(10,10);
        tablePane.setSize(1170,310);
        //tablePane.setVisible(true);

        return tablePane;
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
