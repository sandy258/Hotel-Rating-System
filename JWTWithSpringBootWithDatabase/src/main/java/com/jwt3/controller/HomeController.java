package com.jwt3.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt3.models.User;
import com.jwt3.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getAllUSer();
	}
	
	@GetMapping("/currentUser")
	public String getLoggedInUserDetails(Principal principal) {
		return principal.getName();
	}

}
