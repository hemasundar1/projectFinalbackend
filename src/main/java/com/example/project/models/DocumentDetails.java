package com.example.project.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.web.bind.annotation.CrossOrigin;



@Entity
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String adharNum;
	private String accountNum;
	private String ifscNum;
	private String branch;
	private String rollNum;
	
	
	@OneToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="applicationId", referencedColumnName = "appId")
	private ApplicationDetails applicationDetails;
	
	public DocumentDetails(String adharNum, String accountNum, String ifscNum, String branch,String rollNum) {
		super();
		this.adharNum = adharNum;
		this.accountNum = accountNum;
		this.ifscNum = ifscNum;
		this.branch = branch;
		this.rollNum=rollNum;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public String getAdharNum() {
		return adharNum;
	}



	public void setAdharNum(String adharNum) {
		this.adharNum = adharNum;
	}



	public String getAccountNum() {
		return accountNum;
	}



	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}



	public String getIfscNum() {
		return ifscNum;
	}



	public void setIfscNum(String ifscNum) {
		this.ifscNum = ifscNum;
	}



	public String getBranch() {
		return branch;
	}



	public void setBranch(String branch) {
		this.branch = branch;
	}



	public String getRollNum() {
		return rollNum;
	}



	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
	}




	public DocumentDetails(int id, String adharNum, String accountNum, String ifscNum, String branch, String rollNum,
			ApplicationDetails applicationDetails) {
		super();
		this.id = id;
		this.adharNum = adharNum;
		this.accountNum = accountNum;
		this.ifscNum = ifscNum;
		this.branch = branch;
		this.rollNum = rollNum;
		this.applicationDetails = applicationDetails;
	}

	public DocumentDetails() {
		super();
		
	}
	public ApplicationDetails getApplicationDetails() {
		return applicationDetails;
	}
	public void setApplicationdetails(ApplicationDetails applicationDetails) {
		this.applicationDetails = applicationDetails;
	}
	
	
	
}
