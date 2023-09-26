package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.user.exception.UserNotFoundException;
import com.user.models.User;
import com.user.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	
	@InjectMocks
	private UserServiceImpl UserServicesImpl;
	
	@Mock
	private UserRepo userRepo;
	
	
	@Test
	void UserCreationSuccessTest() {
		User user =User.builder()
		.name("sandesh")
		.email("sandesh@gmail.com")
		.about("Good Person")
		.build();
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, UserServicesImpl.createUser(user));
	}
	
	@Test
	void findUserSuccessTest() throws UserNotFoundException {
		User user =User.builder()
		.name("sandesh")
		.email("sandesh@gmail.com")
		.about("Good Person")
		.build();
		var UserId="dhajansijdnjednjoi";
		when(userRepo.findById(UserId)).thenReturn(Optional.of(user));
		assertEquals(user,UserServicesImpl.getUser(UserId));
	}
	
	
	@Test
	void findUserFailureTest() throws UserNotFoundException {
		var UserId="dhajansijdnjednjoi";
		when(userRepo.findById(UserId)).thenReturn(Optional.empty());
		var exception=assertThrows(UserNotFoundException.class,() ->UserServicesImpl.getUser(UserId));
		assertEquals("User Not Found",exception.getMessage());
	}
	
	
	@Test
	void findNumberOfUsersTest() throws UserNotFoundException {
		User user =User.builder()
		.name("sandesh")
		.email("sandesh@gmail.com")
		.about("Good Person")
		.build();
		
		List<User> Users=new ArrayList<>();
		Users.add(user);
		when(userRepo.findAll()).thenReturn(Users);
		assertEquals(1,UserServicesImpl.getAllUsers().size());
	}
}
