package com.ensonglopedia.Ensonglopedia;

import com.ensonglopedia.view.GUIBuilder;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class EnsonglopediaApplication {

	public static void main(String[] args) {
//		SpringApplication.run(EnsonglopediaApplication.class, args);

        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        GUIBuilder guiBuilder = appContext.getBean("guiBuilder", GUIBuilder.class);
//        //GUIBuilder guiBuilder = new GUIBuilder();
//
        guiBuilder.startGUI();
	}

}
