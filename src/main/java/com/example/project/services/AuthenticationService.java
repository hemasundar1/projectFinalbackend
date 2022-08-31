package com.example.project.services;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Exception.CustomException;
import com.example.project.models.UserModel;
import com.example.project.modelsDto.SignUpResponse;
import com.example.project.modelsDto.UserDto;
import com.example.project.repository.UserRepository;


@Service
public class AuthenticationService {
	
	@Autowired
	UserRepository authrepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Transactional
	public SignUpResponse saveUser(UserDto userdto) {
		
		//check if the user is already present
		if(Objects.nonNull(authrepo.findByEmail(userdto.getEmail()))) {
			//we have an user
			throw new CustomException("User already present");
		}
	
		//hash the password BCryptPasswordEncoder
		String encryptedpassword = getEncodedPassword(userdto.getPassword());

		//save the user
		UserModel submituser = new UserModel(userdto.getUserRole(), userdto.getEmail(), userdto.getUserName(), 
				userdto.getMobileNumber(), encryptedpassword, encryptedpassword);
	//	System.out.println(userdto.getUsername());
		
		authrepo.save(submituser);
		
		
		SignUpResponse response = new SignUpResponse("success", "User added");
		return response;
	}
	

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
   
	

}
