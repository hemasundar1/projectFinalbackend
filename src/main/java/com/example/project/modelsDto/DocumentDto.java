package com.example.project.modelsDto;


public class DocumentDto {
	

	private String adharNum;
	private String accountNum;
	private String ifscNum;
	private String branch;
	private String rollNum;
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
	public DocumentDto(String adharNum, String accountNum, String ifscNum, String branch, String rollNum) {
		super();
		this.adharNum = adharNum;
		this.accountNum = accountNum;
		this.ifscNum = ifscNum;
		this.branch = branch;
		this.rollNum = rollNum;
	}
	public DocumentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DocumentDto [adharNum=" + adharNum + ", accountNum=" + accountNum + ", ifscNum=" + ifscNum + ", branch="
				+ branch + ", rollNum=" + rollNum + "]";
	}

}
