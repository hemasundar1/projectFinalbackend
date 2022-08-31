package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	public UserModel findByEmail(String email);
	public UserModel findByuserName(String userName);
}
