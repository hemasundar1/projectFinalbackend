package com.example.project.modelsDto;

public class UserDto {
	private String userRole;
	private String email;
	private String userName;
	private String mobileNumber;
	private String password;
	private String confirmPassword;
	
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String userRole, String email, String userName, String mobileNumber, String password,
			String confirmPassword) {
		super();
		this.userRole = userRole;
		this.email = email;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
