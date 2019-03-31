package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Controller;

public class FRAMEopenDownloadFilesJ {

	private String command;
	
	private Controller controller;
    
	public static JFrame FrameOpenDownloads;
	private JLabel FrameTitle;
	private JButton downloadCVbutton;
	private JButton downloadLetterButton;
	private JButton acceptButton;
	private JButton declineButton;
	
	public FRAMEopenDownloadFilesJ(Controller controller,String email, String iDinternship) {
		
	this.controller=controller;	
	
	FrameOpenDownloads = new JFrame ("Download window");
	FrameOpenDownloads.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
	FrameOpenDownloads.getContentPane();
	FrameOpenDownloads.pack();
	   
	FrameOpenDownloads.setLayout(null);
	FrameOpenDownloads.setSize(295,175);
	FrameOpenDownloads.setLocationRelativeTo(null);
	
	FrameTitle=new JLabel("See the files uploaded by the student:");
	downloadCVbutton=new JButton("Download CV");
	downloadLetterButton=new JButton("Download Letter");
	acceptButton=new JButton("Accept Student");
	declineButton=new JButton("Decline");
	
	
	FrameOpenDownloads.add(FrameTitle);
	FrameOpenDownloads.add(downloadCVbutton);
	FrameOpenDownloads.add(downloadLetterButton);
	FrameOpenDownloads.add(acceptButton);
	FrameOpenDownloads.add(declineButton);
	
	FrameTitle.setBounds(27, 10, 280, 20);
	downloadCVbutton.setBounds(10, 40, 120, 22);
	downloadLetterButton.setBounds(140, 40, 128, 22);
	acceptButton.setBounds(5, 95, 125, 25);
	declineButton.setBounds(145, 95, 128, 25);


	//ACTION LISTENERS FOR DOWNLOAD
	
	downloadCVbutton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
        	command=new String("cv");
        	String pathForSavingFile=openFileChooserToSelectPath();
        	try {
				File file = controller.getFilesFromDB(command,email,iDinternship,pathForSavingFile);
				Runtime.getRuntime().exec("cmd /c " + file.getAbsolutePath());
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}    	
        }
		
        });
	
	downloadLetterButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
        	command=new String("letter");
        	String pathForSavingFile=openFileChooserToSelectPath();
        	try {
        		File file=controller.getFilesFromDB(command,email,iDinternship,pathForSavingFile);
        		Runtime.getRuntime().exec("cmd /c " + file.getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace();
			} 	
        }
        }); 
	//ACTION LISTENERS FOR ACCEPT/DELETE
	acceptButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
        controller.acceptApplication(email,iDinternship);
        }
        }); 

	declineButton.addActionListener( new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {      	
        controller.declineApplication(email,iDinternship);   
        controller.deleteApplication(email,iDinternship);	    	
        }
        }); 
	

	}//controller finish	
	
	private String openFileChooserToSelectPath() {
		String pathName = null;
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("C:/"));
	    
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	        try {
	            pathName = chooser.getSelectedFile().getAbsolutePath();
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
		return pathName;
	}

			
	public void display() {
		
		FrameOpenDownloads.setVisible(true);
		
		
	}

}
