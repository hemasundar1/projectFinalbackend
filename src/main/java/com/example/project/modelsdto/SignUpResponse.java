package com.example.project.modelsdto;

public class SignUpResponse {

	private String status;
	private String message;
	
	
	
	public SignUpResponse() {
		super();

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
