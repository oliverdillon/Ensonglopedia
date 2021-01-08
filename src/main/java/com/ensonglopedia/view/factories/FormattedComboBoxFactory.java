package com.ensonglopedia.view.factories;

import com.ensonglopedia.view.AbstractInputView;
import com.ensonglopedia.view.SongInputView;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class FormattedComboBoxFactory {

    public static JPanel createComboBox(JComboBox comboBox,String label,String[] comboValues, int xloc, int yloc, AbstractInputView abstractInputView){
        comboBox.setFont(FormattedFontFactory.BodyFont);
        comboBox.setSize(360,50);
        comboBox.addActionListener(abstractInputView);
        comboBox.setSelectedItem("Select Album");
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
    public static void refreshComboBox(JPanel mainPanel, JPanel ComboboxPanel, JComboBox comboBox, String label, String[] comboValues, AbstractInputView abstractInputView){
        mainPanel.remove(comboBox);
        mainPanel.remove(ComboboxPanel);

        JPanel sAlbumBorderPanel= FormattedComboBoxFactory.createComboBox(comboBox,label,comboValues,ComboboxPanel.getX(),ComboboxPanel.getY(), abstractInputView);
        mainPanel.add(sAlbumBorderPanel);
        sAlbumBorderPanel.revalidate();
        sAlbumBorderPanel.repaint();
        mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
        mainPanel.repaint();
    }
}
