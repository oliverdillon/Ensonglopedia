package com.ensonglopedia.view.factories;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

public class FormattedTextBoxFactory {
    public static JPanel createTextBox(JTextField sInputtxt, String label, int xloc, int yloc, KeyListener keyListener, FocusListener focusListener){
        JPanel sInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //input Text box
        sInputtxt.setFont(FormattedFontFactory.BodyFont);
        //sInputtxt.setPreferredSize(new Dimension(150,50));
        sInputtxt.setText(label);
        sInputtxt.setColumns(12);
        sInputtxt.addKeyListener(keyListener);
        sInputtxt.addFocusListener(focusListener);

        sInputBorderPanel.setBorder(BorderFactory.createTitledBorder(FormattedBordersFactory.WhiteLine,
                label, TitledBorder.RIGHT,TitledBorder.TOP, FormattedFontFactory.BodyFont, FormattedColorsFactory.White));
        sInputBorderPanel.setBackground(FormattedColorsFactory.Background);
        sInputBorderPanel.setLocation(xloc,yloc);
        sInputBorderPanel.setSize(360,120);
        sInputBorderPanel.add(sInputtxt);

        return sInputBorderPanel;

    }
}
