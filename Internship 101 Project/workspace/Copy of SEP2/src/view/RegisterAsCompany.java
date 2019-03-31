package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;

public class RegisterAsCompany {
	
	private Controller controller;
	
	private JFrame registerAsCompanyFrame;
	private JLabel registerAsCompanyUsernameLabel;
	private JLabel registerAsComapnyPasswordLabel;
	private JLabel registerAsCompanyRetypePasswordLabel;
	private JButton registerAsCompanyRegisterButton;
	private JTextField registerAsCompanyUsernameField;
	private JPasswordField registerAsCompanyPasswordField;
	private JPasswordField registerAsCompanyRetypePasswordField;
	
	
	public RegisterAsCompany(Controller controller) {
		
		this.controller=controller;
		
		registerAsCompanyFrame = new JFrame ("Company Register");
		registerAsCompanyFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		registerAsCompanyFrame.getContentPane();
		registerAsCompanyFrame.pack();
		
		registerAsCompanyFrame.setLayout(null);
		registerAsCompanyFrame.setSize(600,300);
		registerAsCompanyFrame.setLocationRelativeTo(null);

        registerAsCompanyUsernameLabel=new JLabel("Username:");
        registerAsComapnyPasswordLabel=new JLabel("Password:");
        registerAsCompanyRetypePasswordLabel=new JLabel("Retype Password:");
        
        registerAsCompanyRegisterButton = new JButton("Register");
        registerAsCompanyUsernameField = new JTextField(30);
    	registerAsCompanyPasswordField = new JPasswordField(30);
    	registerAsCompanyRetypePasswordField = new JPasswordField(30);
    	
    	registerAsCompanyUsernameLabel.setBounds(145,75,100,20);
    	registerAsCompanyUsernameField.setBounds(245,75,150,20);
    	registerAsComapnyPasswordLabel.setBounds(145,110,100,20);
    	registerAsCompanyPasswordField.setBounds(245,110,150,20);
    	registerAsCompanyRetypePasswordLabel.setBounds(145,145,110,20);
    	registerAsCompanyRetypePasswordField.setBounds(253,145,142,20);
    	registerAsCompanyRegisterButton.setBounds(255,200,90,25);

    	registerAsCompanyFrame.add(registerAsCompanyRetypePasswordField);
    	registerAsCompanyFrame.add(registerAsCompanyRegisterButton);
    	registerAsCompanyFrame.add(registerAsCompanyRetypePasswordLabel);
    	registerAsCompanyFrame.add(registerAsCompanyUsernameLabel);
    	registerAsCompanyFrame.add(registerAsCompanyUsernameField);
    	registerAsCompanyFrame.add(registerAsComapnyPasswordLabel);
    	registerAsCompanyFrame.add(registerAsCompanyPasswordField);
	
    	
    	registerAsCompanyRegisterButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        	controller.RegisterButtonForCompanyPressed(registerAsCompanyUsernameField.getText(),new String(registerAsCompanyPasswordField.getPassword()), new String(registerAsCompanyRetypePasswordField.getPassword()));
	        }
	        });    	
    	
    	
    	
	}

	public void display() {
		
		registerAsCompanyFrame.setVisible (true);
		
	}

}
