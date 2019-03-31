package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

public class StudentProfile {

	private Controller controller;
	private JFrame StudentProfileFrame;
	private JTextArea outputFromTable;
	private JLabel welcomeLabel;
	private JLabel tablePresentationLabel;
    private JButton apply;
	private JLabel internshipDescriptionLabel;
	private ListSelectionModel listSelectionModel;
	private JButton showHistory;
	
	private JButton logOFF;
	
public StudentProfile(Controller controller, String nameOfStudent, ArrayList<String[]> internshipRaw){
	
	this.controller=controller;
	
	StudentProfileFrame=new JFrame(nameOfStudent+"'s Profile");
	StudentProfileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	StudentProfileFrame.getContentPane();
	StudentProfileFrame.pack();	

	StudentProfileFrame.setLayout(null);
	StudentProfileFrame.setSize(600, 500);
	StudentProfileFrame.setLocationRelativeTo(null);
	
	welcomeLabel=new JLabel("Welcome "+nameOfStudent+" !!");
	tablePresentationLabel=new JLabel("The created internships form all the companies:");
	showHistory=new JButton("Show acceptance history");
	logOFF=new JButton("Log out");
	
	welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 16));
	tablePresentationLabel.setFont(new Font("Serif", Font.ITALIC, 15));
	
	welcomeLabel.setBounds(10, 10, 250, 20);
	tablePresentationLabel.setBounds(10, 42, 400, 20);
	showHistory.setBounds(400, 10, 180, 25); 
	logOFF.setBounds(250, 430, 76, 25);
	
	StudentProfileFrame.add(tablePresentationLabel);
	StudentProfileFrame.add(welcomeLabel);
	StudentProfileFrame.add(showHistory);
	StudentProfileFrame.add(logOFF);
	
	showHistory.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	  
        	controller.CreateFrameForAcceptanceHistoryStudent(nameOfStudent);
        }
    });
	
	logOFF.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	        
        	JOptionPane.showMessageDialog(null,"You will now be redirected to the main menu");
        	StudentProfileFrame.dispose(); //close this window
        	controller.startMainFrame();   //go to main menu
        }
    });

//JTable 
//Convert the ArrayList of Arrays of Strings into 2 Arrays,first=the index of the each row,second=row data
	Object rowData[][] = new String[internshipRaw.size()][7];
	
	for (int i = 0; i < rowData.length; i++) {
		for (int j = 0; j < rowData[i].length; j++) {
			String[] auxiliar = internshipRaw.get(i);
			rowData[i][j] = auxiliar[j];
		}
	}
		
	    Object columnNames[] = { "Title", "Specialization ", "Start date","Duration", "Company name" };
	    JTable table = new JTable(rowData, columnNames);
	    JScrollPane scrollPane = new JScrollPane(table);
	    StudentProfileFrame.add(scrollPane);
	    scrollPane.setBounds(10, 65, 565, 200);  //Size/position of the Jtable
	    
	    
//ListSelectionListener for Jtable
	     class SharedListSelectionHandler implements ListSelectionListener{

		@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm=(ListSelectionModel) e.getSource();
				
				int minIndex = lsm.getMinSelectionIndex();
               int maxIndex = lsm.getMaxSelectionIndex();
               for (int i = minIndex; i <= maxIndex; i++) {
                   if (lsm.isSelectedIndex(i)) {
                	createTextArea((String) rowData[i][6]); //A method implemented down   //GETS THE ID OF THE INTERNSHIP
                   	outputFromTable.append((String) rowData[i][5]);
                   	

        }
	    }
               
	    }
	    }
	    	    
	    listSelectionModel=table.getSelectionModel();
	    listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
	    table.setSelectionModel(listSelectionModel);
	    


}//controller finish

private void createTextArea(String idOfInternship){
	outputFromTable=new JTextArea();
    outputFromTable.setEditable(false);
    outputFromTable.setLineWrap(true);
    outputFromTable.setWrapStyleWord(true);
    JScrollPane outputFromTablePane=new JScrollPane(outputFromTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    StudentProfileFrame.add(outputFromTablePane);
    outputFromTablePane.setBounds(10, 300, 250, 100);
    
    internshipDescriptionLabel=new JLabel("Internship description:");
    apply=new JButton("Apply");
    
    StudentProfileFrame.add(internshipDescriptionLabel);
    StudentProfileFrame.add(apply);
    
    internshipDescriptionLabel.setBounds(10, 275, 200, 25);
    apply.setBounds(10,400, 70, 25);
    
    internshipDescriptionLabel.setFont(new Font("Serif", Font.ITALIC, 15));
    
    apply.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {	
        	controller.applyButtonPressed(idOfInternship);
        }
    });
    
    
}
	
	
	public void display() {
		StudentProfileFrame.setVisible(true);
		
	}

}
