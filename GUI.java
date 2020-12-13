import java.io.*;
import javax.swing.*; 
import java.awt.*;    
import java.awt.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.UIManager.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.ColorUIResource;
import java.lang.Object;
import javax.imageio.ImageIO;
import javax.swing.text.MaskFormatter;

import java.applet.Applet;
import java.awt.image.*;
import java.time.ZonedDateTime;

import OJDevs.ensonglopediaPack.Song;
import OJDevs.ensonglopediaPack.MusicBook;
import OJDevs.ensonglopediaPack.ensonglopedia;

///================================================///
				///CLASS DECLARATION///
///================================================///

public class GUI extends JFrame implements ActionListener, KeyListener, FocusListener{
	//Formatting
	private	Font titleFont;
	private	Font bodyFont;
	private	Font smallFont;
	private	Color background;
	private	Color foreground;
	private	Color white;
	private	Border whiteline;
	private	Border redline;
	private	Border greenline;
	
	//Components
	private JButton addBookbttn;
	private JButton deleteBookbttn;
	private JPanel mainPanel;
	private	JLabel titleLabel;
	private	JPanel sTitleInputBorderPanel;
	private	JPanel sArtistBorderPanel;
	private	JPanel sMusicBookBorderPanel;
	private JTextField sTitleInputtxt;
	private JTextField sArtisttxt;
	private JTextField sMusicBooktxt;
	private JComboBox<String> sMusicBookCombo;
	
	private long secondTime; 
	private long milliTime; 
	
	//Frame
	private JFrame mainWindow;
	private ensonglopedia EN;
	
	//private static final long serialVersionUID = -3088001842L;
	
	///================================================///
					///CONSTRUCTOR///
	///================================================///
	public GUI(){		
		//Initialise
		EN = new ensonglopedia();
		
		//Colours
		white = new Color(255,255,255);
		background = new Color(165,170,184);
		foreground = new Color (68,178,41);
		
		//Fonts
		titleFont = new Font("Ariel",Font.BOLD,50);
		bodyFont = new Font("Ariel",Font.BOLD,30);
		smallFont = new Font("Ariel",Font.BOLD,16);
		
		//Lines
		redline = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red);
		greenline = BorderFactory.createMatteBorder(4, 4, 4, 4, foreground);//new Color(0,204,0));
		redline = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red);
		whiteline = BorderFactory.createMatteBorder(4, 4, 4, 4, white);
		
		
		//Panels
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(background);
		
		//Components
		titleLabel = new JLabel();
		
		sTitleInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sArtistBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sMusicBookBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
 
		sTitleInputtxt = new JTextField();//createFormatter("U***********************")
		sArtisttxt = new JTextField();
		sMusicBooktxt = new JTextField();
		
		//Button
		addBookbttn = new JButton();
		deleteBookbttn = new JButton();
		String[] musicBooks = EN.readMusicBooks();
		sMusicBookCombo = new JComboBox<String>(musicBooks);
		secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
		milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
		
		//Build GUI
		mainPanel = createPanel();
		startGUI(mainPanel);
		
	}
	///================================================///
				///CREATE GUI OBJECT///
	///================================================///
	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}
	private JLabel createTextLabel (String label,int xloc, int yloc){
		JLabel textLabel = new JLabel();
		textLabel.setSize(880, 100);
		textLabel.setForeground(white);
		textLabel.setText(label);
		textLabel.setFont(titleFont);
		//textLabel.setLocation(xloc,yloc); 
		textLabel.setHorizontalAlignment(xloc);
		textLabel.setVerticalAlignment(yloc);
		
		return textLabel;
	}
	private  JComboBox<String> createComboBox (JComboBox<String> comboBox ,int xloc, int yloc){
		comboBox.setLocation(xloc,yloc);
		comboBox.setFont(bodyFont);		
		comboBox.setSize(360,50); 
		comboBox.addActionListener(this);
		comboBox.setSelectedItem("Select Music Book");
		return comboBox;
	}
	private JButton createButton(JButton textbttn, String label,String toolTip, int xloc, int yloc){
		textbttn.addActionListener(this);
		textbttn.setLocation(xloc,yloc); 
		textbttn.setFont(smallFont);
		textbttn.setSize(180,50); 
		textbttn.setText(label);
		textbttn.setToolTipText(toolTip);
		return textbttn;		 
	}
	private JPanel createTextBox (JTextField sInputtxt,String label,int xloc, int yloc){
		JPanel sInputBorderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//input Text box
		sInputtxt.setFont(bodyFont);
		//sInputtxt.setPreferredSize(new Dimension(150,50));
		sInputtxt.setText(label);
		sInputtxt.setColumns(12);
		sInputtxt.addKeyListener(this);
		sInputtxt.addFocusListener(this);
		
		sInputBorderPanel.setBorder(BorderFactory.createTitledBorder(whiteline,
				label,TitledBorder.RIGHT,TitledBorder.TOP,bodyFont,white));
		sInputBorderPanel.setBackground(background);
		sInputBorderPanel.setLocation(xloc,yloc); 
		sInputBorderPanel.setSize(360,120);
		sInputBorderPanel.add(sInputtxt);
		
		return sInputBorderPanel;
	
	}
	private void refreshCombobox(){
		mainPanel.remove(sMusicBookCombo);
		String[] musicBooks = EN.readMusicBooks();
		sMusicBookCombo = new JComboBox<String>(musicBooks);
		sMusicBookCombo= createComboBox (sMusicBookCombo,80,220);
		mainPanel.add(sMusicBookCombo);
		mainPanel.revalidate(); // for JFrame up to Java7 is there only validate()
		mainPanel.repaint();
	}
	
	private JPanel createPanel() //creates the Panel
	{
		//Window Title
		titleLabel= createTextLabel ("Ensonglopedia",JLabel.CENTER,JLabel.CENTER);
		mainPanel.add(titleLabel);		
	
		//Title
		sTitleInputBorderPanel= createTextBox (sTitleInputtxt,"Title",80,100);
		mainPanel.add(sTitleInputBorderPanel);
		
		//Artist
		sArtistBorderPanel= createTextBox (sArtisttxt,"Artist",440,100);
		mainPanel.add(sArtistBorderPanel);
		
		//MusicBook
		//sMusicBookBorderPanel= createTextBox (sMusicBooktxt,"MusicBook",800,100);
		//mainPanel.add(sMusicBookBorderPanel);
		
		//Select Music Book
		sMusicBookCombo= createComboBox (sMusicBookCombo,80,220);
		mainPanel.add(sMusicBookCombo);

		//Button
		addBookbttn= createButton (addBookbttn,"Add Music Book","Click me to add a music book",440,220);
		mainPanel.add(addBookbttn);
		
		//Delete a music book Button
		deleteBookbttn= createButton (deleteBookbttn,"Delete Music Book","Click me to delete a music book",620,220);
		mainPanel.add(deleteBookbttn);

		return mainPanel;
	}
	///================================================///
					///START GUI///
	///================================================///
	private void startGUI(JPanel mainPanel)
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
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();		
		mainWindow.setBounds(d.width/2-600, d.height/2-60, 880, 400); //set position, then dimensions for the main window when the program runs.
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //this allows the program to stop running when the close button is pressed.
		mainWindow.setLayout(new GridLayout(1,1));
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		createPanel();
		mainWindow.add(mainPanel);
		
		
		
	}
	///================================================///
					///MAIN METHOD///
	///================================================///
	public static void main(String[] args) {
		// Invoke the constructor to setup the GUI, by allocating an instance
		/*
		try
		{
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//Gives the program the look and feel of a typical windows program 
			//UIManager.put("TabbedPane.selected",ColorUIResource.GREEN);
			
		}
		catch(Exception e)
		{
			System.out.println("Error with UIManager");
			//If this goes wrong then it catches the error
		}*/
		GUI app = new GUI();
		
	}
	///================================================///
					///ACTION LISTENERS///
	///================================================///
	public void actionPerformed(ActionEvent e) //The Action Listener (performs tasks that are connected to each button)
	{
		if(ZonedDateTime.now().toInstant().getEpochSecond()-secondTime>1){
		//------------------Add Book------------------//
			if(e.getSource() == addBookbttn)
			{			
				File file = new File("Music_book");
				String path = file.getAbsolutePath();
				BufferedImage icon;
				
				try
				{
					icon =ImageIO.read(new File(path+".jpg"));
					//mainWindow.setIconImage();
				}
				catch(IOException io)
				{
					System.out.println("Error Loading Icon");
				}
				
				boolean repeat = true;
				while (repeat==true){
					String MusicBook = JOptionPane.showInputDialog(mainWindow,"Please enter the name of book:",
										"Add Book",JOptionPane.PLAIN_MESSAGE);
					
					//If a string was returned, say so.
					if ((MusicBook != null) && (MusicBook.length() > 0)) {
						EN.addSong("","",MusicBook);
						repeat=false;
					}
					else{
						JOptionPane.showMessageDialog(mainWindow,"Invalid input");
					}
				}
				refreshCombobox();
				
			}
			if(e.getSource() == deleteBookbttn)
			{
				System.out.println("Check");
				String MusicBook = sMusicBookCombo.getSelectedItem().toString();
				Song song = new Song("","",MusicBook);
				EN.deleteBooks(song);
				refreshCombobox();
			}
		}
		secondTime = ZonedDateTime.now().toInstant().getEpochSecond();
	}
	///================================================///
					///KEY LISTENERS///
	///================================================///
	public void keyTyped(KeyEvent e){
		if(ZonedDateTime.now().toInstant().toEpochMilli()-milliTime>50){
			if(e.getKeyChar() == KeyEvent.VK_ENTER)
			{	
				if(e.getSource() == sTitleInputtxt)
					sArtisttxt.requestFocus();
				if(e.getSource() == sArtisttxt){
					sTitleInputtxt.requestFocus();
					String title = sTitleInputtxt.getText();
					String artist = sArtisttxt.getText();
					String MusicBook = sMusicBookCombo.getSelectedItem().toString();
					
					
					if(title.equals("Title")){
						JOptionPane.showMessageDialog(mainWindow, "Please enter a Title");
					}
					else if(artist.equals("Artist")||artist.equals("")){
						JOptionPane.showMessageDialog(mainWindow, "Please enter a Artist");
					}
					else if(MusicBook.equals("Select Music Book")){
						JOptionPane.showMessageDialog(mainWindow, "Please select a Music Book");
					}
					else
					{
						/*
						MusicBook ref;
						try{
							ref = MusicBook.valueOf(MusicBook);
						}
						catch(Exception ex){
							ref =MusicBook.Book2;
							System.out.println("Unknown MusicBook");
						}
						*/
						EN.addSong(title,artist,MusicBook);
						
						sTitleInputtxt.setText("Title");
						sArtisttxt.setText("Artist");
					}
				}
			}
		}
		milliTime = ZonedDateTime.now().toInstant().toEpochMilli();
	}				
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	///================================================///
					///FOCUS LISTENERS///
	///================================================///
	public void focusGained(FocusEvent fe){
		if(fe.getSource() == sTitleInputtxt){
			if(sTitleInputtxt.getText().equals("Title"))
				sTitleInputtxt.setText("");
		}
		if(fe.getSource() == sArtisttxt){
			if(sArtisttxt.getText().equals("Artist"))
				sArtisttxt.setText("");
		}
		if(fe.getSource() == sMusicBooktxt){
			if(sMusicBooktxt.getText().equals("MusicBook"))
				sMusicBooktxt.setText("");
		}
	}
	public void focusLost(FocusEvent fe){
		if(fe.getSource() == sTitleInputtxt){
			if(sTitleInputtxt.getText().equals("")){
				sTitleInputtxt.setText("Title");
			}
		}
		if(fe.getSource() == sArtisttxt){
			if(sArtisttxt.getText().equals("")){
				sArtisttxt.setText("Artist");
			}
		}
		if(fe.getSource() == sMusicBooktxt){
			if(sMusicBooktxt.getText().equals("")){
				sMusicBooktxt.setText("MusicBook");
			}
		}
	}
}

