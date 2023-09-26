package com.jwt3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt3.models.User;



public interface UserRepo  extends JpaRepository<User, String>{
	Optional<User> findByEmail(String email);
}
