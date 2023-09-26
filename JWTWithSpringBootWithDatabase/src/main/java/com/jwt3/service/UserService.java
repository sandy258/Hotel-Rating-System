package com.jwt3.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt3.models.User;
import com.jwt3.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public List<User> getAllUSer(){
		return repo.findAll();
	}

}
