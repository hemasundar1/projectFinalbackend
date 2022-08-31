package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.models.ApplicationDetails;
import com.example.project.models.DocumentDetails;

public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails,Integer> {

public 	ApplicationDetails findByAppId(int appId);
	
	

}
