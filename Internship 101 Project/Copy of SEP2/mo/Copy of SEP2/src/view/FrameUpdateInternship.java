package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;

public class FrameUpdateInternship {

	private Controller controller;
    private JFrame updateInternshipFrame;
	
    private JLabel FrameTitle;
	private JButton titleUpdateButton;
	private JButton specializationUpdateButton;
	private JButton durationUpdateButton;
	private JButton descriptionUpdateButton;
	private JButton startDateUpdateButton;
	private JTextField titleText;
	private JTextField startDateText;
	private JTextField specializationText;
	private JTextField durationText;
	private JTextArea descriptionText;
	private JButton close;
	
	//STRING USED FOR TELLING THE DB WHAT TO UPDATE
	private String command;
	private String updatedData;
	
	
	public FrameUpdateInternship(Controller controller,String internshipTitleSelectedFromTable, String usernameC) {
		
		this.controller=controller;	
		
		updateInternshipFrame = new JFrame ("Update internship");
		updateInternshipFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		updateInternshipFrame.getContentPane();
		updateInternshipFrame.pack();
		   
		updateInternshipFrame.setLayout(null);
		updateInternshipFrame.setSize(335,390);
		updateInternshipFrame.setLocationRelativeTo(null);
		
	    FrameTitle=new JLabel("Update the fields of the selected internship:");
		titleUpdateButton=new JButton("Update the title");
		titleText=new JTextField();
		specializationUpdateButton=new JButton("Udate specialization");
		specializationText=new JTextField();
		startDateUpdateButton = new JButton("Update start date");
		startDateText = new JTextField();
		durationUpdateButton=new JButton("Update duration");
		durationText=new JTextField();
		descriptionUpdateButton=new JButton("Update description:");
		descriptionText=new JTextArea();
		close=new JButton("Close(?)");
		
		close.setToolTipText("Press it to refresh the data and close this window");
		
		//DELIMITATE TEXT when you write in description text area
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		
		updateInternshipFrame.add(FrameTitle);
		updateInternshipFrame.add(titleUpdateButton);
		updateInternshipFrame.add(titleText);
		updateInternshipFrame.add(specializationUpdateButton);
		updateInternshipFrame.add(specializationText);
		updateInternshipFrame.add(startDateUpdateButton);
		updateInternshipFrame.add(startDateText);
		updateInternshipFrame.add(durationUpdateButton);
		updateInternshipFrame.add(durationText);
		updateInternshipFrame.add(descriptionUpdateButton);
		updateInternshipFrame.add(descriptionText);
		updateInternshipFrame.add(close);

		FrameTitle.setBounds(10, 10, 280, 20);
		titleUpdateButton.setBounds(10, 60, 150, 20);   
		titleText.setBounds(165, 60, 145, 20);        
		specializationUpdateButton.setBounds(10, 95, 150, 20);          //special
		specializationText.setBounds(165, 95, 145, 20);
		startDateUpdateButton.setBounds(10, 130, 150, 20);              //date
		startDateText.setBounds(165, 130, 145, 20);
		durationUpdateButton.setBounds(10, 165, 150, 20);               //duration
		durationText.setBounds(165, 165, 145, 20);
		descriptionUpdateButton.setBounds(10, 200, 150, 20);            //description
		descriptionText.setBounds(165, 200, 145, 100);
		close.setBounds(115, 320, 85, 25);
		
		//fonts
		FrameTitle.setFont(new Font("Serif", Font.BOLD, 14));
		
//Close actionListener	
		close.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	   	
	        	CompanyProfile.companyProfileFrame.dispose();
	        	updateInternshipFrame.dispose();
	        	controller.companyLogedInSuccessfully(usernameC);  //start again for refresh table
	        }
        });    	


//All ACTION LISTENERS will use only one method in DB class
		
		titleUpdateButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	     
	        	command=new String("title");
	        	updatedData=new String(titleText.getText());
				controller.updateInternship(internshipTitleSelectedFromTable,usernameC,updatedData,command);
				
	        }
	        });    	

	
	
	specializationUpdateButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	     
        	command=new String("specialization");
        	updatedData=new String(specializationText.getText());
			controller.updateInternship(internshipTitleSelectedFromTable,usernameC,updatedData,command);         }
        });    	

	startDateUpdateButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	     
        	command=new String("start_date");
        	updatedData=new String(startDateText.getText());
			controller.updateInternship(internshipTitleSelectedFromTable,usernameC,updatedData,command);         	
        }
        });    	

	durationUpdateButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	     
        	command=new String("duration");
        	updatedData = new String(durationText.getText());
			controller.updateInternship(internshipTitleSelectedFromTable,usernameC,updatedData,command);       	
        }
        }); 
	
	descriptionUpdateButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	     
        	command=new String("description");
        	updatedData=new String(descriptionText.getText());
			controller.updateInternship(internshipTitleSelectedFromTable,usernameC,updatedData,command);         	
        }
        });    	
}
	
	
	
	public void display() {
		updateInternshipFrame.setVisible(true);		
	}

}
