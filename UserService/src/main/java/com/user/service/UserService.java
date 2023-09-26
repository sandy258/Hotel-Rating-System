package com.user.service;

import java.util.List;

import com.user.exception.UserNotFoundException;
import com.user.models.User;

public interface UserService {
	public User createUser(User user);
	public User getUser(String userId) throws UserNotFoundException;
	public List<User> getAllUsers();
}
