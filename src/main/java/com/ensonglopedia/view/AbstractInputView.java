package com.ensonglopedia.view;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AbstractInputView extends JFrame implements ActionListener, FocusListener, KeyListener {

    ///================================================///
    ///ACTION LISTENERS///
    ///================================================///
    public void actionPerformed(ActionEvent e) {}

    ///================================================///
    ///KEY LISTENERS///
    ///================================================///
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    ///================================================///
    ///FOCUS LISTENERS///
    ///================================================///
    public void focusGained(FocusEvent fe){}
    public void focusLost(FocusEvent fe){}

}
