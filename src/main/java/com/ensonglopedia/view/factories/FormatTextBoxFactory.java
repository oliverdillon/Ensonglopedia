package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.AbstractInputView;
import com.ensonglopedia.view.SongInputView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FormatTextBoxFactory {
    public static JPanel createTextBox(JTextField sInputtxt, String label, int xloc, int yloc, AbstractInputView abstractInputView){
        //input Text box
        sInputtxt.setFont(FormattedFontFactory.BodyFont);
        sInputtxt.setText(label);
        sInputtxt.setColumns(12);
        sInputtxt.addKeyListener(abstractInputView);
        sInputtxt.addFocusListener(abstractInputView);

        JPanel sInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sInputBorderPanel.setBorder(BorderFactory.createTitledBorder(FormattedBordersFactory.WhiteLine,
                label, TitledBorder.RIGHT,TitledBorder.TOP, FormattedFontFactory.BodyFont, FormattedColorsFactory.White));
        sInputBorderPanel.setBackground(FormattedColorsFactory.Background);
        sInputBorderPanel.setLocation(xloc,yloc);
        sInputBorderPanel.setSize(360,120);
        sInputBorderPanel.add(sInputtxt);

        return sInputBorderPanel;

    }
}
