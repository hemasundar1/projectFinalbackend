package com.example.project.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.models.UserModel;
import com.example.project.modelsDto.JwtRequest;
import com.example.project.modelsDto.JwtResponse;
import com.example.project.services.JwtService;
import com.example.project.services.*;



@CrossOrigin(origins = "http://localhost:4200")
@RestController


public class JwtController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userservice;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	System.out.println("Enytered");
        return jwtService.createJwtToken(jwtRequest);
    }
    
    @GetMapping("/current-employee")
    public UserModel getCurrentEmployee(Principal principal)
    {
    	System.out.println(principal.getName());
    	return ((UserModel)this.userservice.getUserByuserName(principal.getName()));
    }
}
