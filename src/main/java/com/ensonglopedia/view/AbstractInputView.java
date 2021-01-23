package com.ensonglopedia.view;

import javax.swing.JFrame;
import java.awt.event.*;

public class AbstractInputView extends JFrame implements ActionListener, FocusListener, KeyListener, MouseListener, ItemListener {

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

    public void itemStateChanged(ItemEvent e) { }

    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}
