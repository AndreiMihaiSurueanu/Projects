package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import model.Application;
import controller.Controller;

public class CompanyProfile {
		
	private Controller controller;
	
    public static JFrame companyProfileFrame;	
	private JLabel welcomeLabel;
	private JLabel tablePresentationLabel;
	private JButton createInternship;
	private JButton showHistory;
	private JButton logOFF;
	
	private JTable table;
	private JScrollPane scrollPane;
	
	private JTextArea outputFromTable;
	private JLabel internshipDescriptionLabel;
	private ListSelectionModel listSelectionModel;
	private JButton deleteButton; 
	private JButton updateButton;
	private String internshipTitleSelectedFromTable;
	private JScrollPane scrollPane1;
	private ListSelectionModel listSelectionModel1;
	
		
	public CompanyProfile(Controller controller, String usernameC,ArrayList<String[]> internshipsFromA_comp){	
		
		
		this.controller=controller;
		
		companyProfileFrame=new JFrame(usernameC+"'s Profile");
		
		companyProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		companyProfileFrame.getContentPane();
		companyProfileFrame.pack();	

		companyProfileFrame.setLayout(null);
		companyProfileFrame.setSize(600, 550);
		companyProfileFrame.setLocationRelativeTo(null);
		
		welcomeLabel=new JLabel("Welcome "+usernameC+" !!");
		tablePresentationLabel=new JLabel("Your internships:");
		createInternship=new JButton("Create internship");
		showHistory=new JButton("Show acceptance history");
		logOFF=new JButton("Log off");
		
		welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 16));
		tablePresentationLabel.setFont(new Font("Serif", Font.ITALIC, 15));
		
		welcomeLabel.setBounds(10, 10, 250, 20);
		tablePresentationLabel.setBounds(10, 42, 400, 20);
		createInternship.setBounds(10, 260, 140, 25);
		showHistory.setBounds(152, 260, 180, 25);                
		logOFF.setBounds(250, 480, 76, 25);
		
		companyProfileFrame.add(welcomeLabel);
		companyProfileFrame.add(tablePresentationLabel);
		companyProfileFrame.add(createInternship);
		companyProfileFrame.add(showHistory);            //history...
		companyProfileFrame.add(logOFF);

	//ACTION LISTENERS
		createInternship.addActionListener( new ActionListener()   //CREATE INTERNSHIP
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
               controller.createInternshipFrame(usernameC);
               
	        }
	    });
		
		showHistory.addActionListener( new ActionListener()    //Show history
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        controller.FrameForAcceptedStudents(usernameC);
	        }
	    });
		
		logOFF.addActionListener( new ActionListener()    //LOGOFF
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        	JOptionPane.showMessageDialog(null,"You will now be redirected to the main menu");
	        	companyProfileFrame.dispose(); //close this window
	        	controller.startMainFrame();   //go to main menu
	        }
	    });
		
		//JTable 
		//Convert the ArrayList of Arrays of Strings into 2 Arrays,first=the index of the each row,second=row data		
		Object rowData[][] = new String[internshipsFromA_comp.size()][7];
		
		for (int i = 0; i < rowData.length; i++) {
			for (int j = 0; j < rowData[i].length; j++) {
				String[] auxiliar = internshipsFromA_comp.get(i);
				rowData[i][j] = auxiliar[j];
			}
		}

		
		  Object columnNames[] = { "Title", "Specialization ", "Start date","Duration" };
		     table = new JTable(rowData, columnNames);
		    scrollPane = new JScrollPane(table);
		    companyProfileFrame.add(scrollPane);
		    scrollPane.setBounds(10, 60, 565, 200);		    //The size for the Jtable
		    
		  //ListSelectionListener for Jtable
		     class SharedListSelectionHandler implements ListSelectionListener{

			@Override
				public void valueChanged(ListSelectionEvent e) {
					ListSelectionModel lsm=(ListSelectionModel) e.getSource();
					
					int minIndex = lsm.getMinSelectionIndex();
	               int maxIndex = lsm.getMaxSelectionIndex();
	               for (int i = minIndex; i <= maxIndex; i++) {
	                   if (lsm.isSelectedIndex(i)) {
	                	  ///must be IN CONTROLLER
	                    String IDinternship=new String((String) rowData[i][6]); //the ID 
	                    
	                	createTextArea(usernameC); //A method implemented down
	                	createRecivedApplicationsTable(IDinternship);//Method for creating a table down
	                	
	                   	outputFromTable.append((String) rowData[i][4]);   //here it gets the description(the index starts from 0 here!!!!!!!!!!)              	
	                   	internshipTitleSelectedFromTable=new String((String) rowData[i][0]);    //Create a string with the title from the internship selected
	             
	                    
	        }//if
		    } //for        
	               
		    }//this method

			
		    }//this mini-class
		    	    
		    listSelectionModel=table.getSelectionModel();
		    listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
		    table.setSelectionModel(listSelectionModel);
		    
		
	}//controller finish
	
	
	private void createTextArea(String usernameC){
		outputFromTable=new JTextArea();
	    outputFromTable.setEditable(false);
	    outputFromTable.setLineWrap(true);
	    outputFromTable.setWrapStyleWord(true);
	    JScrollPane outputFromTablePane=new JScrollPane(outputFromTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    companyProfileFrame.add(outputFromTablePane);
	    outputFromTablePane.setBounds(10, 320, 250, 100);   //Size and position of the description of the internship output ("text area")
	    
	    internshipDescriptionLabel=new JLabel("Internship description:");
	    deleteButton=new JButton("Delete the selected internship");
	    updateButton=new JButton("Update the selected internship");
	    
	    companyProfileFrame.add(internshipDescriptionLabel);
	    companyProfileFrame.add(deleteButton);
	    companyProfileFrame.add(updateButton);
	    
	    internshipDescriptionLabel.setBounds(10, 295, 200, 25);
	    deleteButton.setBounds(10,420, 210, 25);
	    updateButton.setBounds(10,445, 210, 25);
	    
	    internshipDescriptionLabel.setFont(new Font("Serif", Font.ITALIC, 15));
	    //ACTION LISTENERS FOR DELETE AND UPDATE INTERNSHIP
	    
	    deleteButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	        
	        	controller.deleteInternshipSelected(internshipTitleSelectedFromTable,usernameC);
	        	
	        	controller.companyLogedInSuccessfully(usernameC);  //start again for refresh table
	        	
	        }
	    });

	    updateButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {	
	        	controller.createFrameUpdateInternship(internshipTitleSelectedFromTable,usernameC);
	        }
	    });
	}//createTextArea method finish
	
	
	//RECIVED APPLICATION TABLE
	private void createRecivedApplicationsTable(String IDinternship) {
		JLabel tableLabelApplications = new JLabel("Received applications:");
		companyProfileFrame.add(tableLabelApplications);
		tableLabelApplications.setBounds(355, 280, 160, 20);
		
		ArrayList<String[]> applicationsFromDBforSelectedInternship=controller.GETrecivedApplicationsFromDB(IDinternship);
		
Object rowData[][] = new String[applicationsFromDBforSelectedInternship.size()][2];
		
		for (int i = 0; i < rowData.length; i++) {
			for (int j = 0; j < rowData[i].length; j++) {
				String[] auxiliar = applicationsFromDBforSelectedInternship.get(i);
				rowData[i][j] = auxiliar[j];
			}
		}
		 Object columnNames[] = { "Student name", "Student email"};
	     JTable table1 = new JTable(rowData, columnNames);
	     
	     //ListSelectionListener for Jtable
	     class SharedListSelectionHandler1 implements ListSelectionListener{
	    	 
	    	 @Override
	    	 public void valueChanged(ListSelectionEvent xe) {
	    		 ListSelectionModel lsm=(ListSelectionModel) xe.getSource();
	    		 
	    		 int minIndex = lsm.getMinSelectionIndex();
	    		 int maxIndex = lsm.getMaxSelectionIndex();
	    		 for (int i = minIndex; i <= maxIndex; i++) {
	    			 if (lsm.isSelectedIndex(i)) {
	    				 ///TRE SA FACEM IN CONTROLLER
	    				 String email=new String((String) rowData[i][1]); //the ID  //starts at index 0!!!
	    				 
	    				 controller.FRAMEopenDownloadFiles(email,IDinternship); //A method implemented down         
	    				 
	    			 }//if
	    		 } //for        
	    		 
	    	 }//this method
	    	 
	    	 
	     }//this mini-class
	     
	     table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 listSelectionModel1=table1.getSelectionModel();
		 listSelectionModel1.addListSelectionListener(new SharedListSelectionHandler1());
		 table1.setSelectionModel(listSelectionModel1);
		 if(scrollPane1!=null){
		 companyProfileFrame.remove(scrollPane1);
		 }
	     scrollPane1 = new JScrollPane(table1);
	    companyProfileFrame.add(scrollPane1);
	    scrollPane1.setBounds(280, 300, 290, 160);	

		
	     
	     
	}//end of creating table
	
	public void display() {
		companyProfileFrame.setVisible(true);
		
	}

}//class
