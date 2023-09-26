package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.UserNotFoundException;
import com.user.models.User;
import com.user.service.UserServiceImpl;

import jakarta.validation.Valid;


@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userServicesImpl;
	
	@PostMapping
	ResponseEntity<User> createUser(@Valid @RequestBody User User){
		return new ResponseEntity<>(userServicesImpl.createUser(User),HttpStatus.CREATED);
	}
	
	@GetMapping("/{UserId}")
	ResponseEntity<User> getUser(@PathVariable String UserId) throws UserNotFoundException{
		return new ResponseEntity<>(userServicesImpl.getUser(UserId),HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userServicesImpl.getAllUsers(),HttpStatus.OK);
	}
}
