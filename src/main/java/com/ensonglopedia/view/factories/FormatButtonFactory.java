package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.AbstractInputView;
import com.ensonglopedia.view.SongInputView;

import javax.swing.JButton;

public class FormatButtonFactory {
    public static JButton createButton(JButton textbttn, String label, String toolTip, int xloc, int yloc, AbstractInputView abstractInputView){
        textbttn.addActionListener(abstractInputView);
        textbttn.setLocation(xloc,yloc);
        textbttn.setFont(FormattedFontFactory.SmallFont);
        textbttn.setSize(180,50);
        textbttn.setText(label);
        textbttn.setToolTipText(toolTip);
        return textbttn;
    }
}
