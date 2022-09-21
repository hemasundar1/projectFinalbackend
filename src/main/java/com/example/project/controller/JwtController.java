package com.example.project.controller;

import org.apache.log4j.*;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.models.UserModel;
import com.example.project.modelsdto.JwtRequest;
import com.example.project.modelsdto.JwtResponse;
import com.example.project.services.JwtService;
import com.example.project.services.*;



@CrossOrigin(origins = "http://localhost:4200")
@RestController


public class JwtController {
	private static org.apache.log4j.Logger log=Logger.getLogger(JwtController.class);

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService userservice;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) {
    	log.info("Enytered");
        return jwtService.createJwtToken(jwtRequest);
    }
    
    @GetMapping("/current-employee")
    public UserModel getCurrentEmployee(Principal principal)
    {
    	return (this.userservice.getUserByuserName(principal.getName()));
    }
}
