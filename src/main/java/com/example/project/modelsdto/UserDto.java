package com.example.project.modelsdto;

public class UserDto {
	private int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userRole;
	private String email;
	private String userName;
	private String mobileNumber;
	private String password;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String userRole, String email, String userName, String mobileNumber, String password) {
		super();
		this.userRole = userRole;
		this.email = email;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.password = password;
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
}
