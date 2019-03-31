package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;

public class Log_inAsCompany {
	
	
	private Controller controller;

	public static JFrame log_inAsCompanyFrame;
	private JLabel companyUsernameLabel;
	private JLabel companyPasswordLabel;
	private JButton companyLoginButton;
	private JTextField companyuserField;
	private JPasswordField companyPasswordField;
	
	public Log_inAsCompany(Controller controller) {
		
		this.controller = controller;
		
		log_inAsCompanyFrame = new JFrame ("Company Login");
        log_inAsCompanyFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        log_inAsCompanyFrame.getContentPane();
        log_inAsCompanyFrame.pack();
        
        log_inAsCompanyFrame.setLayout(null);
        log_inAsCompanyFrame.setSize(600,300);
        log_inAsCompanyFrame.setLocationRelativeTo(null);
        
        companyUsernameLabel=new JLabel("Username:");
        companyPasswordLabel=new JLabel("Password:");
        
        companyLoginButton = new JButton("Login");
        companyuserField = new JTextField(30);
    	companyPasswordField = new JPasswordField(30);

    	
    	companyUsernameLabel.setBounds(175,80,100,20);
    	companyuserField.setBounds(260,80,150,20);
    	companyPasswordLabel.setBounds(175,115,100,20);
    	companyPasswordField.setBounds(260,115,150,20);
    	companyLoginButton.setBounds(260,150,80,25);

    	
    	log_inAsCompanyFrame.add(companyUsernameLabel);
    	log_inAsCompanyFrame.add(companyuserField);
    	log_inAsCompanyFrame.add(companyPasswordLabel);
    	log_inAsCompanyFrame.add(companyPasswordField);
    	log_inAsCompanyFrame.add(companyLoginButton);
		
    	companyLoginButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        controller.logInButtonForCompanyPressed(companyuserField.getText(), new String(companyPasswordField.getPassword()));	
	        }
	    });
	}

	public void display() {
		log_inAsCompanyFrame.setVisible (true);
		
	}

}
