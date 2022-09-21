package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.models.ApplicationDetails;

@Repository
public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails,Integer> {

public 	ApplicationDetails findByAppId(int appId);

public ApplicationDetails findByName(String name);
	
	

}
