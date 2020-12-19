package com.ensonglopedia.view.factories;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FormattedComboBoxFactory {

    public static JComboBox<String> createComboBox(JComboBox<String> comboBox,String[] comboValues, int xloc, int yloc, ActionListener actionListener){
        comboBox = new JComboBox(comboValues);
        comboBox.setLocation(xloc,yloc);
        comboBox.setFont(FormattedFontFactory.BodyFont);
        comboBox.setSize(360,50);
        comboBox.addActionListener(actionListener);
        comboBox.setSelectedItem("Select Music Book");
        return comboBox;
    }
    public static void refreshComboBox(JPanel mainPanel, JComboBox comboBox, String[] comboValues, ActionListener actionListener){
        mainPanel.remove(comboBox);
        comboBox= FormattedComboBoxFactory.createComboBox(comboBox,comboValues,80,220,actionListener);
        mainPanel.add(comboBox);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
}
