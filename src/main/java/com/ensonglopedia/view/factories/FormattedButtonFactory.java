package com.ensonglopedia.view.factories;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class FormattedButtonFactory {
    public static JButton createButton(JButton textbttn, String label, String toolTip, int xloc, int yloc, ActionListener listener){
        textbttn =new JButton();
        textbttn.addActionListener(listener);
        textbttn.setLocation(xloc,yloc);
        textbttn.setFont(FormattedFontFactory.SmallFont);
        textbttn.setSize(180,50);
        textbttn.setText(label);
        textbttn.setToolTipText(toolTip);
        return textbttn;
    }
}
