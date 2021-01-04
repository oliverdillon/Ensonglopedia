package com.ensonglopedia.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class GUIBuilder {

    @Autowired
    private InputView inputView;

    @Autowired
    private StorageView storageView;

    private JFrame mainWindow;

    private JTabbedPane tabbedPane;

    public void startGUI()
    {
        mainWindow = new JFrame("Ensonglopedia");
        ////////////CREATE WINDOW////////////
        tabbedPane = new JTabbedPane();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        mainWindow.setBounds(d.width/2-600, d.height/2-60, 880, 400); //set position, then dimensions for the main window when the program runs.
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this allows the program to stop running when the close button is pressed.
        mainWindow.setLayout(new GridLayout(1,1));
        mainWindow.setResizable(false);

        tabbedPane.addTab("Input", inputView.createPanel());
        tabbedPane.addTab("Stored", storageView.createPanel());
        mainWindow.add(tabbedPane);
        mainWindow.setVisible(true);
    }
}