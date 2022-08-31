package com.example.project.modelsDto;

import com.example.project.models.UserModel;

public class JwtResponse {
	
    private UserModel user;
    private String jwtToken;
    
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public JwtResponse(UserModel user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
