package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.Controller;

public class Log_inAsMenu {

	private Log_inAsStudent log_inS;
	private Log_inAsCompany log_inC;
	private RegisterMenu registerMenu;
	
	private JFrame mainFrame;
	private JLabel welcome;
	private JPanel panel;
	private JLabel reg;
	private JLabel title;
	private JButton register;
	private JButton loginS;
	private JButton loginC;


	public Log_inAsMenu(Controller controller) {
		mainFrame = new JFrame("Internship 101");
		mainFrame.setSize(610, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		welcome=new JLabel("Welcome!");
		reg = new JLabel("If you don't have an account:");
		register = new JButton("Register");
		title = new JLabel("Login as a:");
		loginS = new JButton("Student");
		loginC = new JButton("Company");
		
		//FONTS
		welcome.setFont(new Font("Serif", Font.ITALIC, 19));
		reg.setFont(new Font("Serif", Font.TRUETYPE_FONT, 16));
		title.setFont(new Font("Serif", Font.TRUETYPE_FONT, 16));
		
		panel.add(welcome);
		panel.add(reg);
		panel.add(register);
		panel.add(title);
		panel.add(loginS);
		panel.add(loginC);

		welcome.setBounds(263,20, 80, 40);
		reg.setBounds(215, 180, 180, 30);
		register.setBounds(250, 215, 100, 30);
		title.setBounds(265,80, 65, 40);
		loginS.setBounds(110, 120, 120, 30);
		loginC.setBounds(370, 120, 120, 30);

		mainFrame.getContentPane().add(panel).setBackground(Color.LIGHT_GRAY);
		
		loginS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				log_inS = new Log_inAsStudent(controller);
				log_inS.display();
				

			}
		});
		
		loginC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				log_inC = new Log_inAsCompany(controller);
				log_inC.display();
				
			}
		});
		
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				registerMenu = new RegisterMenu(controller);
				registerMenu.display();
				
			}
			
		});
	}

	public void display() {
		mainFrame.setVisible(true);

	}

}
