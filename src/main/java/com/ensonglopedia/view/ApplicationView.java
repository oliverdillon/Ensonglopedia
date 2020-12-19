package com.ensonglopedia.view;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class ApplicationView {

    //Frame
    private JFrame mainWindow;
    private InputView inputView;

    public ApplicationView(){
        //Initialise
        startGUI();

    }
    ///================================================///
    ///CREATE GUI OBJECT///
    ///================================================///
    ///================================================///
    ///START GUI///
    ///================================================///
    private void startGUI()
    {
        mainWindow = new JFrame("Ensonglopedia");
/*
THIS IS THE ONLY METHOD WHICH IS CALLED IN THE MAIN METHOD SO THIS METHOD
NEEDS TO CALL ALL OF THE OTHER METHODS NECESSARY FOR THE GUI.
*/
		/*
		File file = new File("Calculator");
		String path = file.getAbsolutePath();

		try
		{
			mainWindow.setIconImage(ImageIO.read(new File(path+".png")));
		}
		catch(IOException io)
		{
			print("Error Loading Icon");
		}
		*/
        ////////////CREATE WINDOW////////////
        inputView = new InputView();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        mainWindow.setBounds(d.width/2-600, d.height/2-60, 880, 400); //set position, then dimensions for the main window when the program runs.
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this allows the program to stop running when the close button is pressed.
        mainWindow.setLayout(new GridLayout(1,1));
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        mainWindow.add(inputView.createPanel());



    }
    public static void main(String[] args){
        ApplicationView app = new ApplicationView();
    }

}