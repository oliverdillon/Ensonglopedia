package com.ensonglopedia.view;

import com.ensonglopedia.dao.ApplicationRepository;
import com.ensonglopedia.service.ApplicationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

//@SpringBootApplication
public class ApplicationView {

    //Frame
    private JFrame mainWindow;
    private InputView inputView;
    private JTabbedPane tabbedPane;
    private StorageView storageView;



    public ApplicationView(){
        //Initialise
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ApplicationRepository applicationRepository= applicationContext.getBean("applicationRepository", ApplicationRepository.class);
        //ApplicationService applicationService = applicationContext.getBean("applicationService",ApplicationService.class);
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
        ////////////CREATE WINDOW////////////
        inputView = new InputView();
        storageView = new StorageView();
        tabbedPane = new JTabbedPane();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        mainWindow.setBounds(d.width/2-600, d.height/2-60, 880, 400); //set position, then dimensions for the main window when the program runs.
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this allows the program to stop running when the close button is pressed.
        mainWindow.setLayout(new GridLayout(1,1));
        mainWindow.setResizable(false);

        tabbedPane.addTab("Input", inputView.createPanel());
        tabbedPane.addTab("Stored", storageView.createPanel());
        mainWindow.add(tabbedPane);
        mainWindow.setVisible(true);



    }
    public static void main(String[] args){
        ApplicationView app = new ApplicationView();
    }

}
