package view;
import java.awt.EventQueue;
import model.*;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import controller.*;
public class View {

	private JFrame frame;
	private JTextField cityField;
	private Controller controller;
	private String currentTemp;
	private String longDescription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View(new Controller(new Model( new Weather("London"))));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View(Controller c) {
		initialize();
		this.controller = c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 355, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		JPanel first = new JPanel();
		//frame.getContentPane().add(first, "home");
		JPanel cards = new JPanel(new CardLayout()); 
		cards.add(first, "home");
		frame.getContentPane().add(cards);
		first.setLayout(null);
		
		ImageIcon icon = new ImageIcon("images/blueBack.png");
		
		cityField = new JTextField();
		cityField.setForeground(new Color(128, 128, 128));
		cityField.setText("Enter a city...");
		cityField.setBounds(22, 292, 315, 50);
		first.add(cityField);
		cityField.setColumns(10);
		
		JLabel weatherLbl = new JLabel("Let me get you the weather");
		weatherLbl.setHorizontalAlignment(SwingConstants.CENTER);
		weatherLbl.setForeground(new Color(255, 255, 255));
		weatherLbl.setFont(new Font("Avenir Next", Font.BOLD, 22));
		weatherLbl.setBounds(22, 179, 315, 65);
		first.add(weatherLbl);
		
		JLabel greetingLbl = new JLabel("Hey, friend!");
		greetingLbl.setFont(new Font("Avenir Next", Font.BOLD, 53));
		greetingLbl.setForeground(new Color(255, 255, 255));
		greetingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		greetingLbl.setBounds(12, 85, 331, 106);
		first.add(greetingLbl);
		
		
		JButton showWeatherPane = new JButton("Let's go!");
		showWeatherPane.setFont(new Font("Avenir Next", Font.BOLD, 15));
		showWeatherPane.setBounds(92, 435, 170, 42);
		first.add(showWeatherPane);
		JLabel background = new JLabel(new ImageIcon("/Users/Adam/Documents/BRUM/SoftwareWorkshop/ViewTest/images/blueBack.png"));
		background.setBounds(0, 0, 355, 578);
		first.add(background);
		
		
		
		//---------------------------SECOND SCREEN --------------------------------------
		JPanel second = new JPanel();
		//frame.getContentPane().add(second, "weatherDisplay");
		cards.add(second, "weatherDisplay");
		second.setLayout(null);
		
		JLabel currentTempLbl = new JLabel("11");
		currentTempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentTempLbl.setFont(new Font("Avenir Next", Font.BOLD, 99));
		currentTempLbl.setForeground(new Color(255, 255, 255));
		currentTempLbl.setBounds(6, 111, 330, 168);
		second.add(currentTempLbl);
		
		JLabel tempLbl = new JLabel("Temperature:");
		tempLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tempLbl.setForeground(new Color(255, 255, 255));
		tempLbl.setFont(new Font("Avenir Next", Font.BOLD, 18));
		tempLbl.setBounds(30, 39, 306, 60);
		second.add(tempLbl);
		
		JLabel tempDescriptionText = new JLabel("It's");
		tempDescriptionText.setForeground(new Color(255, 255, 255));
		tempDescriptionText.setHorizontalAlignment(SwingConstants.CENTER);
		tempDescriptionText.setFont(new Font("Avenir Next", Font.BOLD, 24));
		tempDescriptionText.setBounds(30, 291, 289, 45);
		second.add(tempDescriptionText);
		
		JLabel tempBackground = new JLabel("New label");
		tempBackground.setIcon(new ImageIcon("/Users/Adam/Documents/BRUM/SoftwareWorkshop/ViewTest/images/blueBack1.jpg"));
		tempBackground.setBounds(0, 0, 355, 578);
		second.add(tempBackground);
		
		showWeatherPane.addActionListener(new ActionListener()
	    {
		       public void actionPerformed(ActionEvent e)
		       {
		    	   String city = cityField.getText();
		    	   if(controller.validCity(city)) {
		    		   currentTemp = controller.getTemp();
		    		   longDescription = controller.getDesc().substring(0, 1).toUpperCase() + controller.getDesc().substring(1);
		    		   tempLbl.setText("Current tempertaure in " + cityField.getText()+ ":"  );
		    		   currentTempLbl.setText(currentTemp);
		    		   tempDescriptionText.setText(longDescription);
		    		   
		    		   
		    		   CardLayout cardLayout = (CardLayout) cards.getLayout();
		    		   cardLayout.show(cards, "weatherDisplay");
		    		 
		    	   }else {
		    		   JOptionPane.showMessageDialog(frame, "Please Enter a valid City");
		    	   }
		    	   
		    	   }
		       
		       });
		
		
	}
}
