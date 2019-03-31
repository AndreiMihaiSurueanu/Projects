package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import controller.Controller;

public class CreateInternshipFrame {

	private Controller controller;
	
	public static JFrame CREATE_internship;
		
	private JLabel TIP;
	private JLabel titleLabel;
	private JLabel specializationLabel;
	private JLabel durationLabel;
	private JLabel descriptionLabel;
	private JLabel startDateLabel;
	private JTextField titleText;
	private JTextField startDateText;
	private JTextField specializationText;
	private JTextField durationText;
	private JTextArea descriptionText;
	private JButton create;
	
	public CreateInternshipFrame(Controller controller,String usernameC) {
		this.controller=controller;	

	CREATE_internship = new JFrame ("Create an internship");
	CREATE_internship.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	CREATE_internship.getContentPane();
	CREATE_internship.pack();
	   
	CREATE_internship.setLayout(null);
	CREATE_internship.setSize(300,385);
	CREATE_internship.setLocationRelativeTo(null);
	
	TIP=new JLabel("Please fill-in all the fields: ");
	titleLabel=new JLabel("Internship title:");
	titleText=new JTextField();
	specializationLabel=new JLabel("Specialization:");
	specializationText=new JTextField();
	startDateLabel = new JLabel("(?)Starting date:");
	startDateText = new JTextField();
	durationLabel=new JLabel("(?)Duration:");
	durationText=new JTextField();
	descriptionLabel=new JLabel("(?)Description:");
	descriptionText=new JTextArea();
	create=new JButton("CREATE");
	
	//DELIMITATE TEXT when you write in description text area
	descriptionText.setLineWrap(true);
	descriptionText.setWrapStyleWord(true);
	

	
	CREATE_internship.add(TIP);
	CREATE_internship.add(titleLabel);
	CREATE_internship.add(titleText);
	CREATE_internship.add(specializationLabel);
	CREATE_internship.add(specializationText);
	CREATE_internship.add(startDateLabel);
	CREATE_internship.add(startDateText);
	CREATE_internship.add(durationLabel);
	CREATE_internship.add(durationText);
	CREATE_internship.add(descriptionLabel);
	CREATE_internship.add(descriptionText);
	CREATE_internship.add(create);
	
	TIP.setBounds(50, 10, 180, 20);
	titleLabel.setBounds(15, 70, 90, 20);   
	titleText.setBounds(110, 70, 155, 20);        
	specializationLabel.setBounds(15, 105, 90, 20);          //special
	specializationText.setBounds(110, 105, 155, 20);
	startDateLabel.setBounds(10, 140, 100, 20);              //date
	startDateText.setBounds(110, 140, 70, 20);
	durationLabel.setBounds(23, 175, 90, 20);               //duration
	durationText.setBounds(110, 175, 30, 20);
	descriptionLabel.setBounds(15, 210, 90, 20);            //description
	descriptionText.setBounds(110, 210, 155, 70);
	create.setBounds(100, 300, 80, 30);
	//fonts
	TIP.setFont(new Font("Serif", Font.BOLD, 15));
	
	//TIPS
	durationLabel.setToolTipText("In months");
	descriptionLabel.setToolTipText("A short description of the internship");
	startDateLabel.setToolTipText("day-month-year");
	
	create.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	        
        	
				controller.CreateInternshipButtonPressed(titleText.getText(),specializationText.getText(),startDateText.getText(),Integer.parseInt( durationText.getText()),descriptionText.getText(),usernameC);
				controller.companyLogedInSuccessfully(usernameC);  //start again for refresh table
        	
        }
        });    	
	
	
	}//constructor finish
	

	
	public void display() {
		CREATE_internship.setVisible(true);		
	}

}
