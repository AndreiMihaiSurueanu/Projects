package controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Application;
import model.Company;
import model.Internship;
import model.Student;
import storage.DB;
import view.*;

public class Controller {

	private Log_inAsMenu mainFrame;
	private DB theDataBaseConnection;
	private Student student;
	private Company company;
	private Internship internship;

	public Controller(DB AdataBase) { // when the controller object is created
										// you pass a db object as parameter to
										// the constructor of the controller
		mainFrame = new Log_inAsMenu(this);
		theDataBaseConnection = AdataBase;
		student = new Student();
		company = new Company();
		internship = new Internship();
	}

	public void startProgram() {
		mainFrame.display();
	}

	// LOGIN FOR STUDENTS-takes as arguments email and password from the
	// Log_in_AsStudent
	public void logInButtonForStudentPressed(String emailS, String passwordS) {

		if (theDataBaseConnection.searchForLog_inStudent(emailS, passwordS)) {
			JOptionPane.showMessageDialog(null, "Login successfull");
			Log_inAsStudent.log_inAsStudentFrame.dispose();
			studentLogedInSuccessfully(emailS, passwordS); // sends
			// emailS
			// as
			// parameter
			// for
			// Student
			// Profile
		} else {
			JOptionPane.showMessageDialog(null,
					"Email or password wrong,try again");
		}

	}

	// LOGIN FOR COMPANYs
	public void logInButtonForCompanyPressed(String usernameC, String passwordC) {

		company.setUsernameForLogin(usernameC);
		company.setPasswordForLogin(passwordC);

		if(theDataBaseConnection.searchForLog_inCompany(usernameC, passwordC)){
			JOptionPane.showMessageDialog(null, "Login successfull");
			Log_inAsCompany.log_inAsCompanyFrame.dispose();
			companyLogedInSuccessfully(usernameC);
		} else {
		
			JOptionPane.showMessageDialog(null,"Username or password wrong,try again");

		}
		}

	

	// REGISTER FOR STUDENTS

	public void RegisterButtonForStudentPressed(String name, String email,
			String passwordSR, String passwordSRR) {

		student.setName(name);
		student.setEmailForRegister(email);
		student.setPasswordForRegiter(passwordSR);

		if (passwordSR.equals(passwordSRR)) {
			theDataBaseConnection.RegisterForStudent(name, email, passwordSR,
					passwordSRR);
			RegisterMenu.registerMenuFrame.dispose();
			registeredSuccessfully(); 
			
		}
		else {
			JOptionPane.showMessageDialog(null,
					"You have failed to retype password ");
		}
	}

	// REGISTER FOR COMPANY
	public void RegisterButtonForCompanyPressed(String usernameCR,
			String passwordCR, String passwordCRR) {

		company.setUsernameForRegister(usernameCR);
		company.setPasswordForRegister(passwordCR);
		
		if (passwordCR.equals(passwordCRR)) {
		theDataBaseConnection.RegisterForCompany(usernameCR, passwordCR,
				passwordCRR);
RegisterMenu.registerMenuFrame.dispose();
registeredSuccessfully();

	}else {
		JOptionPane.showMessageDialog(null,
				"You have failed to retype password ");
	}
	}
	
	
	// Register successfully button pressed
	public void registeredSuccessfully() {
		RegisteredOkFrame registeredOkFrame = new RegisteredOkFrame(this);
		registeredOkFrame.display();

	}

	// "Go to LOGIN" button pressed
	public void startMainFrame() {
		mainFrame.display();
	}

	// STUDENT PROFILE
	public void studentLogedInSuccessfully(String emailS, String passwordS) { // gets
																				// emailS
																				// as
																				// parameter
																				// from
																				// "searchForLog_inStudent"
																				// method(DB)

		student.setEmailForLogin(emailS);
		student.setPasswordForLogin(passwordS);
		student.setName(theDataBaseConnection
				.getNameFromStudent_forLogin(emailS)); // sends Email to DB in
														// order to get the name
														// from DB\\

		ArrayList<String[]> internshipRaw = theDataBaseConnection.getInternshipsFromDB();
		ArrayList<String[]> acceptenceHistoryStudent = theDataBaseConnection.getInternshipsFromDB();

		StudentProfile studentProfile = new StudentProfile(this,student.getName(), internshipRaw);
		studentProfile.display();

	}

	// COMPANY PROFILE
	public void companyLogedInSuccessfully(String usernameC) {

		company.setUsernameForLogin(usernameC);
		ArrayList<String[]> internshipRawsFromAcompany = theDataBaseConnection
				.getInternshipsForComanyFromDB(usernameC);

		CompanyProfile companyProfile = new CompanyProfile(this, usernameC,
				internshipRawsFromAcompany);
		companyProfile.display();
	}

	// Frame for creating an Internship
	public void createInternshipFrame(String usernameC) {

		CreateInternshipFrame createInternshipFrame = new CreateInternshipFrame(
				this, usernameC);
		createInternshipFrame.display();

	}

	// Insert into DB a new Internship
	public void CreateInternshipButtonPressed(String title,
			String specialization, String date, int duration,
			String description, String usernameC) {

		theDataBaseConnection.CreateInternship(title, specialization, date,
				duration, description, usernameC);

		internship.setTitle(title);
		internship.setSpecialization(specialization);
		internship.setStartDate(date);
		internship.setDuration(duration);
		internship.setDescription(description);
		internship.setCompanyName(usernameC);

	}

	// DELETE THE SELECTED INTERNSHIP
	public void deleteInternshipSelected(
			String internshipTitleSelectedFromTable, String usernameC) {

		theDataBaseConnection.deleteInternship(
				internshipTitleSelectedFromTable, usernameC);

	}

	// JFRAME FOR UPDATE INTERNSHIP SELECTED
	public void createFrameUpdateInternship(
			String internshipTitleSelectedFromTable, String usernameC) {

		FrameUpdateInternship frameUpdateInternship = new FrameUpdateInternship(
				this, internshipTitleSelectedFromTable, usernameC);
		frameUpdateInternship.display();

	}

	// UPDATE INTERNSHIP SELECTED
	public void updateInternship(String internshipTitleSelectedFromTable,
			String usernameC, String updatedData, String command) {
		theDataBaseConnection.updateInternshipSelected(
				internshipTitleSelectedFromTable, usernameC, updatedData,
				command);

	}

	// Apply for an internship
	public void applyButtonPressed(String idOfInternship) {
		FrameForApply frameForApply = new FrameForApply(this, idOfInternship);
		frameForApply.display();
	}

	// Send application
	public void sendApplicationButtonPressed(File cvFile, File letterFile,
			String idOfInternship) {

		Application application = new Application(cvFile, letterFile,
				student.getEmailForLogin());

		theDataBaseConnection.saveApplicationinDB(cvFile, letterFile,
				student.getEmailForLogin(), idOfInternship);

	}

	public ArrayList<String[]> GETrecivedApplicationsFromDB(String iDinternship) {

		return theDataBaseConnection
				.GETapplicationsFromDBforSelectedInternship(iDinternship);
	}

	// Frame for downloading
	public void FRAMEopenDownloadFiles(String email, String iDinternship) {

		FRAMEopenDownloadFilesJ frame = new FRAMEopenDownloadFilesJ(this,
				email, iDinternship);
		frame.display();

	}

	// Get files for Download
	public File getFilesFromDB(String command, String email,
			String iDinternship, String pathForSavingFile) throws IOException {

		return theDataBaseConnection.getFilesFromDBsql(command, email,
				iDinternship, pathForSavingFile);
	}

	// Decline application(followed by deleting it in the next method)
	public void declineApplication(String email, String iDinternship) {

		theDataBaseConnection.declineApplication(email, iDinternship);
	}

	// Delete application
	public void deleteApplication(String email, String iDinternship) {

		theDataBaseConnection
				.deleteApplicationAfterDecline(email, iDinternship);

	}

	// Accept application
	public void acceptApplication(String email, String iDinternship) {

		theDataBaseConnection.acceptApplication(email, iDinternship);
		theDataBaseConnection
				.deleteApplicationAfterDecline(email, iDinternship);

	}

	// Get accepted students
	public void FrameForAcceptedStudents(String usernameC) {
		ArrayList<String[]> acceptedStudents = theDataBaseConnection
				.acceptedStudents(usernameC);

		FrameForAcceptedStudents acc = new FrameForAcceptedStudents(usernameC,
				acceptedStudents);
		acc.display();

	}

	public void CreateFrameForAcceptanceHistoryStudent(String nameOfStudent) {
		
		ArrayList<String[]> acceptenceHistoryStudent = theDataBaseConnection.getAcceptanceHistoryStudent(nameOfStudent);
		FrameForAcceptanceHistoryStudent frame=new FrameForAcceptanceHistoryStudent(acceptenceHistoryStudent);		
		frame.display();
	}

}
