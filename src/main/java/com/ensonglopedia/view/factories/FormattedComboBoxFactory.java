package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.WindowView;

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
}
