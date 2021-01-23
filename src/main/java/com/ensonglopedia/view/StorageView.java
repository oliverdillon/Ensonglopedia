package com.ensonglopedia.view;

import com.ensonglopedia.entities.VinylObject;
import com.ensonglopedia.repository.SongBookRepository;
import com.ensonglopedia.entities.SongObject;
import com.ensonglopedia.repository.VinylRepository;
import com.ensonglopedia.view.factories.FormattedColorsFactory;
import com.ensonglopedia.view.factories.FormattedFontFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StorageView extends AbstractInputView {

    @Autowired
    private SongBookRepository songBookRepository;

    @Autowired
    private VinylRepository vinylRepository;

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JScrollPane tablePane;

    private JComboBox selectionCombo = new JComboBox();

    public JPanel createPanel() //creates the Panel
    {
        //Panels
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(FormattedColorsFactory.Background);

        String[] comboValues = {"Vinyls","Music Books"};

        selectionCombo.setFont(FormattedFontFactory.BodyFont);
        selectionCombo.setSelectedItem(comboValues[0]);
        selectionCombo.addItemListener(this);
        selectionCombo.setModel(new DefaultComboBoxModel(comboValues));
        mainPanel.add(selectionCombo);

        tablePane = BuildTable();
        mainPanel.add(tablePane);

        return mainPanel;
    }
    public void refreshTable(String selection){
        refreshTable(mainPanel,tablePane,selection);
    }
    public void refreshTable(JPanel mainPanel, JScrollPane tablePane, String selection){
        mainPanel.remove(tablePane);
        tablePane = BuildTable(selection);
        mainPanel.add(tablePane);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
    public JScrollPane BuildTable (){
        return BuildTable ("");
    }

    public JScrollPane BuildTable (String selection){

        int  i=0;
        int size;
        Object[][] data;
        String[] columnNames;

        switch (selection) {
            case "SongBook": {
                size = songBookRepository.getSongObjects().size();
                data = new Object[size][3];

                columnNames = new String[]{"Song", "Artist", "Album"};


                for (SongObject s : songBookRepository.getSongObjects()) {
                    data[i][0] = s.getTitle();
                    data[i][1] = s.getAlbumDet().getArtist();
                    data[i][2] = s.getAlbumDet().getAlbum();
                    i++;
                }
            }
            default:{
                size = vinylRepository.getVinylObjects().size();
                data = new Object[size][3];

                columnNames = new String[]{"Artist", "Album","Release Year"};

                for (VinylObject s : vinylRepository.getVinylObjects()) {
                    data[i][0] = s.getAlbumDet().getArtist();
                    data[i][1] = s.getAlbumDet().getAlbum();
                    data[i][2] = s.getReleaseDate();
                    i++;
                }
            }
        }

        JTable songTable =new JTable(data,columnNames);
        songTable.setGridColor(Color.white);
        songTable.getTableHeader().setBackground(Color.white);
        songTable.addMouseListener(this);
        songTable.addKeyListener(this);

        //uses the member's personal info and the headings to create the members table
        JScrollPane tablePane = new JScrollPane(songTable); //creates a new scroll pane containing the members table
        tablePane.setLocation(10,40);
        tablePane.setSize(1170,280);
        //tablePane.setVisible(true);

        return tablePane;
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            if(e.getSource() == selectionCombo) {
                refreshTable((String)selectionCombo.getSelectedItem());
            }
        }
    }

}
