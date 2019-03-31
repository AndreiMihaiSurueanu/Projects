package view;

import java.awt.event.*;

import javax.swing.*;

import controller.Controller;

public class RegisterMenu {
	
	private Controller controller;
	
	private RegisterAsStudent registerAsStudent;
	private RegisterAsCompany registerAsCompany;
	
	public static JFrame registerMenuFrame;
	private JLabel registerMenuLabel;
	private JButton registerMenuStudentButton;
	private JButton registerMenuCompanyButton;
	

	public RegisterMenu(Controller controller) {
		
		this.controller=controller;
		
		registerMenuFrame = new JFrame("Register");
		registerMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registerMenuFrame.getContentPane();
		registerMenuFrame.pack();
		

		registerMenuFrame.setLayout(null);
		registerMenuFrame.setSize(600, 300);
		registerMenuFrame.setLocationRelativeTo(null);

		registerMenuLabel = new JLabel("Register as a:");
		registerMenuStudentButton = new JButton("Student");
		registerMenuCompanyButton = new JButton("Company");

		registerMenuFrame.add(registerMenuLabel);
		registerMenuFrame.add(registerMenuStudentButton);
		registerMenuFrame.add(registerMenuCompanyButton);

		registerMenuLabel.setBounds(250, 80, 100, 40);
		registerMenuStudentButton.setBounds(100, 130, 140, 30);
		registerMenuCompanyButton.setBounds(360, 130, 140, 30);

		// REGISTER STUDENT
		registerMenuStudentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				registerAsStudent = new RegisterAsStudent(controller);
				registerAsStudent.display();
				
			}
		});

		// REGISTER COMPANY
		registerMenuCompanyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				registerAsCompany = new RegisterAsCompany(controller);
				registerAsCompany.display();
				
			}
		});

		
	}
	
	public void display() {
		registerMenuFrame.setVisible(true);
	}
	
	

}
