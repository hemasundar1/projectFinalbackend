package com.example.project.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;


@CrossOrigin(origins = "http://localhost:4200")
@Entity
@Table(name = "Users")

public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	
	private String userRole;
	
	@Column(unique = true)
	private String email;
	
	private String userName;
	
	private String mobileNumber;
	
	private String password;
	
	private String confirmPassword;
	
	
	
	
	@OneToOne(mappedBy= "userModel" , fetch = FetchType.EAGER , orphanRemoval = true)
	@JsonIgnore
	private ApplicationDetails application_details;
	



	public ApplicationDetails getApplication_details() {
		return application_details;
	}

	public void setApplication_details(ApplicationDetails application_details) {
		this.application_details = application_details;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public void setUserName(String userName) {
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




	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel( String userRole, String email, String userName, String mobileNumber, String password,
			String confirmPassword) {
		super();
		
		this.userRole = userRole;
		this.email = email;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public UserModel(int userId) {
		super();
		this.userId = userId;
	}
	
	
	
}
