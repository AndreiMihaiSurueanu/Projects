package storage;

import java.util.ArrayList;

public interface IDB {
	public boolean searchForLog_inStudent(String emailS, String passwordS);
	public String getNameFromStudent_forLogin(String emailS);
	public boolean searchForLog_inCompany(String usernameC, String passwordC);
	public void RegisterForStudent(String name, String email,
			String passwordSR, String passwordSRR);
	public void RegisterForCompany(String usernameCR, String passwordCR,
			String passwordCRR);
	public void CreateInternship(String title, String specialization,
			String date, int duration, String description, String usernameC);
	public ArrayList<String[]> getInternshipsFromDB();
	public ArrayList<String[]> getInternshipsForComanyFromDB(String usernameC);
	public void deleteInternship(String internshipTitleSelectedFromTable,
			String usernameC);
	public void updateInternshipSelected(
			String internshipTitleSelectedFromTable, String usernameC,
			String updatedData, String command);
	public ArrayList<String[]> GETapplicationsFromDBforSelectedInternship(
			String iDinternship);
	public void declineApplication(String email, String iDinternship);
	public void deleteApplicationAfterDecline(String email, String iDinternship);
	public void acceptApplication(String email, String iDinternship);
	public ArrayList<String[]> acceptedStudents(String usernameC);
}
