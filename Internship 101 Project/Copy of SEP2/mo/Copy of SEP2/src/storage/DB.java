package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.postgresql.Driver;
import org.postgresql.largeobject.LargeObjectManager;

import view.CompanyProfile;
import view.CreateInternshipFrame;
import view.FRAMEopenDownloadFilesJ;
import view.Log_inAsCompany;
import view.Log_inAsMenu;
import view.Log_inAsStudent;
import view.RegisterAsCompany;
import view.RegisterAsStudent;
import view.RegisterMenu;
import controller.Controller;

public class DB implements IDB {
	private Driver driver;
	private Connection conn = null;
	private Connection connection;

	public DB() throws SQLException {
		driver = new Driver();
		DriverManager.registerDriver(driver);
		conn = DriverManager
				.getConnection(
						"jdbc:postgresql://localhost:5432/postgres",
						"postgres", "maximilian9");
		JOptionPane
				.showMessageDialog(null,
						"The connection to the Database has been successfully established");

		conn = dbConnection();
	}

	private Connection dbConnection() throws SQLException {

		try {
			Class.forName("org.postgresql.Driver");
			return conn;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;

		}

	}

	// LOG IN STUDENT
	public boolean searchForLog_inStudent(String emailS, String passwordS) {
		try {
			String querry = "SELECT * FROM Student WHERE email=? AND password=?";
			PreparedStatement preparedSt = conn.prepareStatement(querry);
			preparedSt.setString(1, emailS);
			preparedSt.setString(2, passwordS);

			ResultSet rs = preparedSt.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = count + 1;
			}

			rs.close(); // because just on connection at a time
			preparedSt.close();

			return count == 1;
	
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	// Takes the email as arguments and checks for the name corresponding to
	// that email
	public String getNameFromStudent_forLogin(String emailS) {

		String studentName = null;

		try {

			String query = "SELECT * FROM Student WHERE email=?";
			PreparedStatement preparedSt = conn.prepareStatement(query);
			preparedSt.setString(1, emailS);

			ResultSet rs = preparedSt.executeQuery();

			rs.next();

			studentName = rs.getString("name");

			preparedSt.close();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return studentName;

	}

	// LOGIN FOR COMPANY
	public boolean searchForLog_inCompany(String usernameC, String passwordC) {
		try {
			String querry = "SELECT * FROM Company WHERE nameC=? AND password=?";
			PreparedStatement preparedSt = conn.prepareStatement(querry);
			preparedSt.setString(1, usernameC);
			preparedSt.setString(2, passwordC);

			ResultSet rs = preparedSt.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = count + 1;
			}
			rs.close(); // because just one connection at a time
			preparedSt.close();

			return count==1;

			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	// REGISTER FOR STUDENT
	public void RegisterForStudent(String name, String email,
			String passwordSR, String passwordSRR) {
		try {

			String Update = "INSERT INTO Student (name,password,email)VALUES(?,?,?)";
			PreparedStatement preparedSt = conn.prepareStatement(Update);
			preparedSt.setString(1, name);
			preparedSt.setString(2, passwordSR);
			preparedSt.setString(3, email);

			preparedSt.executeUpdate();
			preparedSt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// REGISTER FOR COMPANY
	public void RegisterForCompany(String usernameCR, String passwordCR,
			String passwordCRR) {
		

			try {

				String Update = "INSERT INTO Company (nameC,password,accepted_students_email,accepted_students_name) VALUES(?,?,'','')";
				PreparedStatement preparedSt = conn.prepareStatement(Update);
				preparedSt.setString(1, usernameCR);
				preparedSt.setString(2, passwordCR);

				preparedSt.executeUpdate();

				preparedSt.close();

				

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e);
			}

		}

	// CREATE INTERNSHIP
	public void CreateInternship(String title, String specialization,
			String date, int duration, String description, String usernameC) {

		try {

			String Update = "INSERT INTO Internship (title,companyName,specialization,start_date,duration,description) VALUES(?,?,?,?,?,?)";
			PreparedStatement preparedSt = conn.prepareStatement(Update);
			preparedSt.setString(1, title);
			preparedSt.setString(2, usernameC);
			preparedSt.setString(3, specialization);
			preparedSt.setObject(4, date);
			preparedSt.setInt(5, duration);
			preparedSt.setString(6, description);

			preparedSt.executeUpdate();

			preparedSt.close();

			JOptionPane.showMessageDialog(null, "Internship created");
			CreateInternshipFrame.CREATE_internship.dispose();
			CompanyProfile.companyProfileFrame.dispose(); // close the window

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}

	}

	// FOR STUDENTS TO DISPLAY TABLE IN THE PROFILE
	public ArrayList<String[]> getInternshipsFromDB() {

		ArrayList<String[]> internships = new ArrayList<String[]>();

		try {

			String query = "SELECT title,specialization,start_date,duration,description,companyname,ID FROM Internship";
			PreparedStatement preparedSt = conn.prepareStatement(query);
			ResultSet rs = preparedSt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String specialization = rs.getString(2);
				String startDate = rs.getString(3);
				int durationINT = rs.getInt(4);
				String description = rs.getString(5);
				String companyName = rs.getString(6);
				int IdFromInternshipINT = rs.getInt(7);

				String IdFromInternship = String.valueOf(IdFromInternshipINT);
				String duration = String.valueOf(durationINT);

				String[] internship = new String[] { title, specialization,
						startDate, duration, companyName, description,
						IdFromInternship };
				internships.add(internship);

			}

			preparedSt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}

		return internships;
	}

	// FOR COMAPNY TO DISPLAY

	public ArrayList<String[]> getInternshipsForComanyFromDB(String usernameC) {

		ArrayList<String[]> internshipsFromC = new ArrayList<String[]>();

		try {

			String query = "SELECT title,specialization,start_date,duration,description,companyname,ID FROM Internship WHERE companyName=?";
			PreparedStatement preparedSt = conn.prepareStatement(query);
			
			preparedSt.setString(1, usernameC); 
			
			ResultSet rs = preparedSt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String specialization = rs.getString(2);
				String startDate = rs.getString(3);
				int durationINT = rs.getInt(4);
				String description = rs.getString(5);
				String companyname = rs.getString(6);
				int IDint = rs.getInt(7);

				String duration = String.valueOf(durationINT);
				String ID = String.valueOf(IDint);

				String[] internship = new String[] { title, specialization,
						startDate, duration, description, companyname, ID };

				internshipsFromC.add(internship);

			}

			preparedSt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}

		return internshipsFromC; // wich is the ArrayList of Arrays of String we
									// have just created so we get brainf*cked
	}

	// Delete internship

	public void deleteInternship(String internshipTitleSelectedFromTable,
			String usernameC) {

		try {

			String delete = "DELETE FROM Internship where title=? AND companyname=? ";
			PreparedStatement preparedSt = conn.prepareStatement(delete);

			preparedSt.setString(1, internshipTitleSelectedFromTable);
			preparedSt.setString(2, usernameC);

			int rs = preparedSt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Internship deleted");
			CompanyProfile.companyProfileFrame.dispose(); // close the window

			preparedSt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	} // delete method end

	// The method used by all the ActionListeners from UpdateInternshipFrame
	public void updateInternshipSelected(
			String internshipTitleSelectedFromTable, String usernameC,
			String updatedData, String command) {

		try {

			String update = ("UPDATE Internship  SET " + command + " =? WHERE title =?  AND companyName=? "); // HOW?
			PreparedStatement preparedSt = conn.prepareStatement(update);
if(command.equals("duration")){
int udatedDuration=Integer.valueOf(updatedData)	;

			preparedSt.setInt(1, udatedDuration);
			preparedSt.setString(2, internshipTitleSelectedFromTable);
			preparedSt.setString(3, usernameC);
} else{
	preparedSt.setString(1, updatedData);
	preparedSt.setString(2, internshipTitleSelectedFromTable);
	preparedSt.setString(3, usernameC);

}
			preparedSt.executeUpdate();

			JOptionPane.showMessageDialog(null, command + " was updated");

			preparedSt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// Save application to the Database
	public void saveApplicationinDB(File cvFile, File letterFile,
			String emailOfStudent, String idOfInternship) {
		try {

			FileInputStream cvStream = new FileInputStream(cvFile);
			FileInputStream letterStream = new FileInputStream(letterFile);

			int IDinternship = Integer.valueOf(idOfInternship);

			String apply = "INSERT INTO Applications(cv,letter,email,ID_internship) VALUES(?,?,?,?)";
			PreparedStatement preparedSt = conn.prepareStatement(apply);

			// HERE WE NEED TO ADD IN DATABASE TWO COLUMNS WITH CV_NAME AND
			// LETTER_NAME, IN THESE COLUMNS WE PUT CV.GETNAME() AND
			// LETTER.GETNAME()

			preparedSt.setBinaryStream(1, cvStream, cvFile.length());
			preparedSt.setBinaryStream(2, letterStream, letterFile.length());
			preparedSt.setString(3, emailOfStudent);
			preparedSt.setInt(4, IDinternship);

			preparedSt.executeUpdate();

			JOptionPane.showMessageDialog(null,"You have successfully applied for the selected internship");

			cvStream.close();
			letterStream.close();
			preparedSt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}

	}

	// GET APPLICATIONS
	public ArrayList<String[]> GETapplicationsFromDBforSelectedInternship(
			String iDinternship) {

		ArrayList<String[]> applicationsFromDBforSelectedInternship = new ArrayList<String[]>();

		try {

			int iDinternshipINT = Integer.valueOf(iDinternship);

			String query = "SELECT name,email FROM Applications JOIN Student USING(email) WHERE ID_internship = ?";
			PreparedStatement preparedSt = conn.prepareStatement(query);

			preparedSt.setInt(1, iDinternshipINT);

			ResultSet rs = preparedSt.executeQuery();

			while (rs.next()) {

				String name = rs.getString(1);
				String email = rs.getString(2);

				String[] internship = new String[] { name, email };

				applicationsFromDBforSelectedInternship.add(internship);

			}

			preparedSt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
		return applicationsFromDBforSelectedInternship;
	}

	// get the requested file

	public File getFilesFromDBsql(String command, String email,
			String iDinternship, String pathForSavingFile) throws IOException {

		int iDinternshipINT = Integer.valueOf(iDinternship);

		File file = null;
		FileOutputStream fileOuputStream = null;

		// HERE WE NEED TO GET FROM DATABASE ALSO THE COLUMNS CV_NAME AND
		// LETTER_NAME AND THEN USE THEM TO CREATE THE FILE THAT WE DOWNLOAD

		try {
			String querry = ("Select " + command + " FROM Applications WHERE id_internship=? AND email=?");
			PreparedStatement ps = conn.prepareStatement(querry);

			ps.setInt(1, iDinternshipINT);
			ps.setString(2, email);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				byte[] fileBytes = rs.getBytes(1);

				if (command.equals("cv")) {
					try {
						file = new File(pathForSavingFile + ".txt");
						fileOuputStream = new FileOutputStream(file);
						fileOuputStream.write(fileBytes);

					} finally {
						fileOuputStream.close();
					}
				}// IF CLOSE

				if (command.equals("letter")) {
					try {
						file = new File(pathForSavingFile + email + ".txt");
						fileOuputStream = new FileOutputStream(file);
						fileOuputStream.write(fileBytes);

					} finally {
						fileOuputStream.close();
					}
				}// IF CLOSE

			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return file;

	}

	// DECLINE APPLICATION

	public void declineApplication(String email, String iDinternship) {
		try {
			int iDinternshipINT = Integer.valueOf(iDinternship);

			String decline = "INSERT INTO Accepted(email,ID_internship,response) VALUES(?,?,?)";
			PreparedStatement preparedSt = conn.prepareStatement(decline);

			preparedSt.setString(1, email);
			preparedSt.setInt(2, iDinternshipINT);
			preparedSt.setString(3, "declined");

			preparedSt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Application declined");

			preparedSt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	// DELETE APPLICATION AFTER DECLINE

	public void deleteApplicationAfterDecline(String email, String iDinternship) {
		try {
			int iDinternshipINT = Integer.valueOf(iDinternship);

			String delete = "DELETE FROM Applications where email=? AND ID_internship=? ";
			PreparedStatement preparedSt = conn.prepareStatement(delete);

			preparedSt.setString(1, email);
			preparedSt.setInt(2, iDinternshipINT);

			int rs = preparedSt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Application was deleted");
			FRAMEopenDownloadFilesJ.FrameOpenDownloads.dispose();
			FRAMEopenDownloadFilesJ.FrameOpenDownloads.dispose(); // close the
																	// window

			preparedSt.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	// Accept application
	public void acceptApplication(String email, String iDinternship) {

		try {
			int iDinternshipINT = Integer.valueOf(iDinternship);

			String decline = "INSERT INTO Accepted(email,ID_internship,response) VALUES(?,?,?)";
			PreparedStatement preparedSt = conn.prepareStatement(decline);

			preparedSt.setString(1, email);
			preparedSt.setInt(2, iDinternshipINT);
			preparedSt.setString(3, "accepted");

			preparedSt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Application accepted");

			preparedSt.close();

			FRAMEopenDownloadFilesJ.FrameOpenDownloads.dispose();
			FRAMEopenDownloadFilesJ.FrameOpenDownloads.dispose(); // close the
																	// window

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public ArrayList<String[]> acceptedStudents(String usernameC) {

		ArrayList<String[]> applicationsFromDBforSelectedInternship = new ArrayList<String[]>();

		try {

			String query = "SELECT response,name FROM Accepted JOIN Student USING(email) JOIN Internship ON ID=ID_internship WHERE companyName = ?";
			PreparedStatement preparedSt = conn.prepareStatement(query);

			preparedSt.setString(1,usernameC);

			ResultSet rs = preparedSt.executeQuery();

			while (rs.next()) {

				String response = rs.getString(1);
				String name = rs.getString(2);

				String[] application = new String[] { response, name };

				applicationsFromDBforSelectedInternship.add(application);

			}

			preparedSt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
		return applicationsFromDBforSelectedInternship;
	}

	public ArrayList<String[]> getAcceptanceHistoryStudent(String nameOfStudent) {
		ArrayList<String[]> acceptenceHistory = new ArrayList<String[]>();
		try {

			String query = "SELECT response,companyName FROM Accepted JOIN Student Using(email) JOIN Internship ON ID=ID_internship   WHERE name = ?";
			PreparedStatement preparedSt = conn.prepareStatement(query);

			preparedSt.setString(1,nameOfStudent);

			ResultSet rs = preparedSt.executeQuery();

			while (rs.next()) {

				String response = rs.getString(1);
				String nameC = rs.getString(2);

				String[] raw = new String[] { response, nameC };

				acceptenceHistory.add(raw);

			}

			preparedSt.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
		}
		return acceptenceHistory;
	}
}
