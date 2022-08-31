package com.example.project.modelsDto;

public class SignUpResponse {

	private String status;
	private String message;
	
	
	
	public SignUpResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SignUpResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "SignUpResponse [status=" + status + ", message=" + message + "]";
	}
	
	
	
	
}
