package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;

public class Log_inAsStudent {
	
	private Controller controller;
	
	public static JFrame log_inAsStudentFrame;
	private JLabel studentMailLabel;
	private JLabel studentPasswordLabel;
	private JButton log_inButton;
	private JTextField studentEmailField;
	private JPasswordField studentPasswordField;

	public Log_inAsStudent(Controller controller) {
		
		this.controller = controller;
		
		log_inAsStudentFrame = new JFrame("Log-In as a Student");
		log_inAsStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		log_inAsStudentFrame.getContentPane();
		log_inAsStudentFrame.pack();
		

		log_inAsStudentFrame.setLayout(null);
		log_inAsStudentFrame.setSize(600, 300);
		log_inAsStudentFrame.setLocationRelativeTo(null);

		studentMailLabel = new JLabel("Email:");
		studentPasswordLabel = new JLabel("Password:");

		log_inButton = new JButton("Login");
		studentEmailField = new JTextField(30);
		studentPasswordField = new JPasswordField(30);

		studentMailLabel.setBounds(175, 80, 100, 20);
		studentEmailField.setBounds(260, 80, 150, 20);
		studentPasswordLabel.setBounds(175, 115, 100, 20);
		studentPasswordField.setBounds(260, 115, 150, 20);
		log_inButton.setBounds(260, 150, 80, 25);

		log_inAsStudentFrame.add(studentMailLabel);
		log_inAsStudentFrame.add(studentEmailField);
		log_inAsStudentFrame.add(studentPasswordField);
		log_inAsStudentFrame.add(studentPasswordLabel);
		log_inAsStudentFrame.add(log_inButton);
		
		
		
		
		log_inButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	
	        controller.logInButtonForStudentPressed(studentEmailField.getText(), new String(studentPasswordField.getPassword()));	
	        }
	    });
				
	}

	public void display() {
		log_inAsStudentFrame.setVisible(true);
		
		
	}
	
	
	
	


}
