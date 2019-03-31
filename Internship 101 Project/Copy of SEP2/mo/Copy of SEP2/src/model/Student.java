package model;

public class Student {
	
	private String emailForLogin;
	private String emailForRegister;
	private String passwordForLogin;
	private String passwordForRegiter;
	private String name;
	
	public String getEmailForLogin() {
		return emailForLogin;
	}

	public void setEmailForLogin(String emailForLogin) {
		this.emailForLogin = emailForLogin;
	}

	public String getEmailForRegister() {
		return emailForRegister;
	}

	public void setEmailForRegister(String emailForRegister) {
		this.emailForRegister = emailForRegister;
	}

	public String getPasswordForLogin() {
		return passwordForLogin;
	}

	public void setPasswordForLogin(String passwordForLogin) {
		this.passwordForLogin = passwordForLogin;
	}

	public String getPasswordForRegiter() {
		return passwordForRegiter;
	}

	public void setPasswordForRegiter(String passwordForRegiter) {
		this.passwordForRegiter = passwordForRegiter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
