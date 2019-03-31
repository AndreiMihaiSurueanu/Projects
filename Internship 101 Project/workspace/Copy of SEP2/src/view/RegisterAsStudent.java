package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;

public class RegisterAsStudent {
	
	private Controller controller;

	
	private JFrame registerAsStudentFrame;
	private JLabel registerAsStudentNameLabel;
	private JLabel registerAsStudentEmailLabel;
	private JLabel registerAsStudentPasswordLabel;
	private JLabel registerAsStudentRetypePasswordLabel;
	private JButton registerAsStudentRegisterButton;
	private JTextField registerAsStudentNameField;
	private JTextField registerAsStudentEmailField;
	private JPasswordField RegisterAsStudentPasswordField;
	private JPasswordField registerAsStudentRetypePasswordField;
	
	public RegisterAsStudent(Controller controller) {
		
		this.controller = controller;

		
		registerAsStudentFrame = new JFrame ("Student Register");
		registerAsStudentFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		registerAsStudentFrame.getContentPane();
		registerAsStudentFrame.pack();
		
        
		registerAsStudentFrame.setLayout(null);
		registerAsStudentFrame.setSize(600,300);
		registerAsStudentFrame.setLocationRelativeTo(null);

		registerAsStudentNameLabel=new JLabel("Name:");
        registerAsStudentEmailLabel=new JLabel("Email:");
        registerAsStudentPasswordLabel=new JLabel("Password:");
        registerAsStudentRetypePasswordLabel=new JLabel("Retype Password:");
        
        registerAsStudentRegisterButton = new JButton("Register");
        registerAsStudentNameField = new JTextField(30);
        registerAsStudentEmailField = new JTextField(30);
    	RegisterAsStudentPasswordField = new JPasswordField(30);
    	registerAsStudentRetypePasswordField = new JPasswordField(30);

    	
    	registerAsStudentNameLabel.setBounds(145,40,100,20);
    	registerAsStudentNameField.setBounds(245,40,150,20);
    	registerAsStudentEmailLabel.setBounds(145,75,100,20);
    	registerAsStudentEmailField.setBounds(245,75,150,20);
    	registerAsStudentPasswordLabel.setBounds(145,110,100,20);
    	RegisterAsStudentPasswordField.setBounds(245,110,150,20);
    	registerAsStudentRetypePasswordLabel.setBounds(145,145,110,20);
    	registerAsStudentRetypePasswordField.setBounds(253,145,142,20);
    	registerAsStudentRegisterButton.setBounds(255,200,90,25);

    	registerAsStudentFrame.add(registerAsStudentRetypePasswordField);
    	registerAsStudentFrame.add(registerAsStudentRegisterButton);
    	registerAsStudentFrame.add(registerAsStudentRetypePasswordLabel);
    	registerAsStudentFrame.add(registerAsStudentEmailField);
    	registerAsStudentFrame.add(registerAsStudentEmailLabel);
    	registerAsStudentFrame.add(registerAsStudentPasswordLabel);
    	registerAsStudentFrame.add(RegisterAsStudentPasswordField);
    	registerAsStudentFrame.add(registerAsStudentNameLabel);
    	registerAsStudentFrame.add(registerAsStudentNameField);
    	
    	
    	registerAsStudentRegisterButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        	controller.RegisterButtonForStudentPressed(new String(registerAsStudentNameField.getText()),registerAsStudentEmailField.getText(), new String(RegisterAsStudentPasswordField.getPassword()), new String(registerAsStudentRetypePasswordField.getPassword()));
	        		
	        }
	        });    	
	        	
	        	
	}

	public void display() {
		registerAsStudentFrame.setVisible (true);
	}

}
