package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.models.User;

public interface UserRepo extends JpaRepository<User, String>{

}
