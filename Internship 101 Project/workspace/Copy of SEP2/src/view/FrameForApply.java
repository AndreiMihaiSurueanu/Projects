package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;




import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.Controller;

public class FrameForApply {

	private Controller controller;
    private JFrame JFrameForApply;
    private JButton uploadCVbutton;
    private JButton uploadLetterButton;
    private JButton apply;
    private File cvFile;
    private File letterFile;
    
    private JLabel FrameTitle;
	private JFileChooser jFileChooser;
	
	public  FrameForApply(Controller controller, String idOfInternship){
		
		this.controller=controller;	
		
		JFrameForApply = new JFrame ("Apply for internship");
		JFrameForApply.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		JFrameForApply.getContentPane();
		JFrameForApply.pack();
		   
		JFrameForApply.setLayout(null);
		JFrameForApply.setSize(256,175);
		JFrameForApply.setLocationRelativeTo(null);
		
		FrameTitle=new JLabel("Apply for the selected internship:");
		uploadCVbutton=new JButton("Upload CV");
		uploadLetterButton=new JButton("Upload Letter");
		apply=new JButton("Apply");
		
		JFrameForApply.add(FrameTitle);
		JFrameForApply.add(uploadCVbutton);
		JFrameForApply.add(uploadLetterButton);
		JFrameForApply.add(apply);
		
		FrameTitle.setBounds(25, 10, 280, 20);
		uploadCVbutton.setBounds(10, 40, 100, 25);
		uploadLetterButton.setBounds(120, 40, 110, 25);
		apply.setBounds(80, 100, 70, 30);
		
		uploadCVbutton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	cvFile = createFileChooser();
	        	
	        }
	        }); 
		uploadLetterButton.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	letterFile = createFileChooser();
	        	
	        }
	        }); 
      
      //Action Listener for apply button
		apply.addActionListener( new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	if(cvFile!=null && letterFile!=null ){
	        	controller.sendApplicationButtonPressed(cvFile,letterFile,idOfInternship);
	        	}
	        	else{ JOptionPane.showMessageDialog(null, "Please upload both files");
	        	
	        	}
	        }
	        }); 
		
	}
	
	
	
	
	
	private File createFileChooser() {
		File aRandomFile = null;
		
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new java.io.File("C:/"));
		jFileChooser.setDialogTitle("Select your file!");
		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if (jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			
			aRandomFile = jFileChooser.getSelectedFile(); 
			
		}
		
		return aRandomFile;
	}





	public void display() {
		JFrameForApply.setVisible(true);
		
	}

}
