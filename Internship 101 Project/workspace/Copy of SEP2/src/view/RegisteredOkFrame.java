package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;

public class RegisteredOkFrame {
	
	private JFrame registerOK;
	private JButton goToLoginMenuButton;
	private JLabel congratsLabel;
	private Controller controller;
	
	public RegisteredOkFrame(Controller controller){
		
		this.controller=controller;
		
		registerOK = new JFrame("Register successfully");
		registerOK.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registerOK.getContentPane();
		registerOK.pack();
		

		registerOK.setLayout(null);
		registerOK.setSize(300, 150);
		registerOK.setLocationRelativeTo(null);

		congratsLabel=new JLabel("You have registered successfully!");
		goToLoginMenuButton = new JButton("Go to Login");
		
		goToLoginMenuButton.setBounds(95, 55, 100, 30);
		congratsLabel.setBounds(50, 10, 200, 30);

		//FONTS
		congratsLabel.setFont(new Font("Serif", Font.BOLD, 13));

		
		registerOK.add(goToLoginMenuButton);
		registerOK.add(congratsLabel);
		
		goToLoginMenuButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	  
	       	registerOK.dispose();
	        controller.startMainFrame();	
	        }
	    });

	}
	
	

	
	
	public void display() {
		
		registerOK.setVisible(true);	
	}

}
