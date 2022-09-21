package com.example.project.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.models.UserModel;
import com.example.project.modelsdto.UserDto;
import com.example.project.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@GetMapping("/admin/list")
	public List<UserModel> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping("/admin/list/{userId}")
	public UserModel getUserById(@PathVariable Integer userId){
		return userService.getUserById(userId);
	}
	
	@PostMapping("/admin/create")
	public void createEmployee(@RequestBody UserDto user)
	{
		UserModel user1=modelMapper.map(user, UserModel.class);
		userService.save(user1);
	}
	
	@PutMapping("/admin/updateEmployee/{id}")
    public void updateEmployee(@PathVariable Integer id,@RequestBody UserDto userEmp) {
		UserModel userEmployee=modelMapper.map(userEmp, UserModel.class);
		if(userEmployee.getUserRole().equals("Employee")) {
        UserModel useremployee = userService.getUserById(id); 
        useremployee.setUserName(userEmployee.getUserName());
        useremployee.setMobileNumber(userEmployee.getMobileNumber());
        useremployee.setEmail(userEmployee.getEmail());
        userService.save(useremployee);
		}
    }
	
	@PutMapping("/student/updateStudent")
    public void updateStudent(@RequestBody UserDto userStu) {
		UserModel userStudent=modelMapper.map(userStu, UserModel.class);
		if(userStudent.getUserRole().equals("Student")) {
			Integer id=userStudent.getUserId();
        UserModel userstudent = userService.getUserById(id); 
        userstudent.setUserName(userStudent.getUserName());
        userstudent.setMobileNumber(userStudent.getMobileNumber());
        userstudent.setEmail(userStudent.getEmail());
        userService.save(userstudent);
		}
    }
	
	@DeleteMapping("/admin/delete/{id}")
	public void deleteEmployee(@PathVariable Integer id)
	{
		userService.deleteEmployee(id);
	}

	
	@DeleteMapping("/User")
	public String deleteUser(@RequestParam(value="id" , required=true) int id) {
		return userService.deleteUser(id);
	}
	
}
