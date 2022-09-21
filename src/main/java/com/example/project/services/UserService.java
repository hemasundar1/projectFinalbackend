package com.example.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.project.models.UserModel;
import com.example.project.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void save(UserModel useremployee)
	{	
		userRepo.save(useremployee);
	}
	
	public List<UserModel> getAllUsers()
	{
		return userRepo.findAll();
	}

	public UserModel getUserById(Integer id){
		return userRepo.findByuserId(id);
	}
	
	public void deleteEmployee(Integer id)
	{
		userRepo.deleteById(id);
	}
		
	public String deleteUser(int id) {
		
		try {
			userRepo.deleteById(id);
			return "Successfully Deleted";
		}
		catch(Exception e) {
			return "Not deleted , Please try again";
		
		}
		
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public UserModel getUserByuserName(String email)
	{
		return userRepo.findByEmail(email);
	}
}
