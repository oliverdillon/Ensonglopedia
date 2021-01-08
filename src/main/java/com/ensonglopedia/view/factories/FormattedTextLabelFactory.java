package com.ensonglopedia.view.factories;

import javax.swing.*;

public class FormattedTextLabelFactory {
    public static JLabel createTextLabel(String label, int xloc, int yloc){
        JLabel textLabel = new JLabel();
        textLabel.setSize(880, 100);
        textLabel.setForeground(FormattedColorsFactory.White);
        textLabel.setText(label);
        textLabel.setFont(FormattedFontFactory.TitleFont);
        textLabel.setHorizontalAlignment(xloc);
        textLabel.setVerticalAlignment(yloc);

        return textLabel;
    }
}
