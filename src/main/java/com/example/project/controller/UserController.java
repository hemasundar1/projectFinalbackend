package com.example.project.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.project.repository.UserRepository;
import com.example.project.services.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/admin/list")
	public List<UserModel> getAllUsers()
	{
		return (List<UserModel>)userService.getAllUsers();
	}
	
	@GetMapping("/admin/list/{userId}")
	public UserModel getUserById(@PathVariable Integer userId){
		return userService.getUserById(userId);
	}
	
	@PostMapping("/admin/create")
	public void createEmployee(@RequestBody UserModel user)
	{
		System.out.println("post controller employee");
		userService.save(user);
	}
	
	@PutMapping("/admin/updateEmployee/{id}")
    public void updateEmployee(@PathVariable Integer id,@RequestBody UserModel userEmployee) {
		System.out.println(userEmployee.getUserRole());
		if(userEmployee.getUserRole().equals("Employee")) {
        UserModel useremployee = userService.getUserById(id); 
        useremployee.setUserName(userEmployee.getUserName());
        useremployee.setMobileNumber(userEmployee.getMobileNumber());
        useremployee.setEmail(userEmployee.getEmail());
        System.out.println(userEmployee.getMobileNumber());
        userService.save(useremployee);
		}
    }
	
	@PutMapping("/student/updateStudent")
    public void updateStudent(@RequestBody UserModel userStudent) {
		System.out.println(userStudent.getUserRole());
		if(userStudent.getUserRole().equals("Student")) {
			Integer id=userStudent.getUserId();
        UserModel userstudent = userService.getUserById(id); 
        userstudent.setUserName(userStudent.getUserName());
        userstudent.setMobileNumber(userStudent.getMobileNumber());
        userstudent.setEmail(userStudent.getEmail());
        System.out.println(userStudent.getMobileNumber());
        userService.save(userstudent);
		}
    }
	
	@DeleteMapping("/admin/delete/{id}")
	public void deleteEmployee(@PathVariable Integer id)
	{
		System.out.println("Deleting in controller");
		userService.deleteEmployee(id);
	}

  /*  @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}*/

	
	@DeleteMapping("/User")
	public String deleteUser(@RequestParam(value="id" , required=true) int id) {
		return userService.deleteUser(id);
	}
	
}
