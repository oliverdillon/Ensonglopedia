package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.InputView;

import javax.swing.JButton;

public class FormatButtonFactory {
    public static JButton createButton(JButton textbttn, String label, String toolTip, int xloc, int yloc, InputView inputView){
        textbttn.addActionListener(inputView);
        textbttn.setLocation(xloc,yloc);
        textbttn.setFont(FormattedFontFactory.SmallFont);
        textbttn.setSize(180,50);
        textbttn.setText(label);
        textbttn.setToolTipText(toolTip);
        return textbttn;
    }
}
