package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.InputView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FormattedComboBoxFactory {

    public static JComboBox<String> createComboBox(JComboBox<String> comboBox,String[] comboValues, int xloc, int yloc, InputView inputView){
        comboBox = new JComboBox(comboValues);
        comboBox.setLocation(xloc,yloc);
        comboBox.setFont(FormattedFontFactory.BodyFont);
        comboBox.setSize(360,50);
        comboBox.addActionListener(inputView);
        comboBox.setSelectedItem("Select Music Book");
        return comboBox;
    }
    public static void refreshComboBox(JPanel mainPanel, JComboBox comboBox, String[] comboValues, InputView inputView){
        mainPanel.remove(comboBox);
        comboBox= FormattedComboBoxFactory.createComboBox(comboBox,comboValues,80,220,inputView);
        mainPanel.add(comboBox);
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
}
