package model;

import java.io.File;

public class Application {
    
	private String ID;
	private File cvFile;
	private File letterFile;
	private String StudentEmail;
	
	
	public Application(File cvFile, File letterFile, String emailForLogin) {
		this.ID=ID;
		this.cvFile=cvFile;
		this.letterFile=letterFile;
		this.StudentEmail=emailForLogin;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public File getCvFile() {
		return cvFile;
	}
	public void setCvFile(File cvFile) {
		this.cvFile = cvFile;
	}
	public File getLetterFile() {
		return letterFile;
	}
	public void setLetterFile(File letterFile) {
		this.letterFile = letterFile;
	}
	public String getStudentEmail() {
		return StudentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
	}
	
}
