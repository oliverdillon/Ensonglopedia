package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.InputView;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class FormattedComboBoxFactory {

    public static JPanel createComboBox(JComboBox comboBox,String label,String[] comboValues, int xloc, int yloc, InputView inputView){
        comboBox.setFont(FormattedFontFactory.BodyFont);
        comboBox.setSize(360,50);
        comboBox.addActionListener(inputView);
        comboBox.setSelectedItem("Select Music Book");
        comboBox.setModel(new DefaultComboBoxModel(comboValues));

        JPanel sInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sInputBorderPanel.setBorder(BorderFactory.createTitledBorder(FormattedBordersFactory.WhiteLine,
                label, TitledBorder.RIGHT,TitledBorder.TOP, FormattedFontFactory.BodyFont, FormattedColorsFactory.White));
        sInputBorderPanel.setBackground(FormattedColorsFactory.Background);
        sInputBorderPanel.setLocation(xloc,yloc);
        sInputBorderPanel.setSize(360,120);
        sInputBorderPanel.add(comboBox);

        return sInputBorderPanel;
    }
    public static void refreshComboBox(JPanel mainPanel, JPanel ComboboxPanel, JComboBox comboBox, String label, String[] comboValues, InputView inputView){
        mainPanel.remove(comboBox);
        mainPanel.remove(ComboboxPanel);

        JPanel sMusicBookBorderPanel= FormattedComboBoxFactory.createComboBox(comboBox,label,comboValues,ComboboxPanel.getX(),ComboboxPanel.getY(),inputView);
        mainPanel.add(sMusicBookBorderPanel);
        sMusicBookBorderPanel.revalidate();
        sMusicBookBorderPanel.repaint();
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
}
