package com.java.SpringBootProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.SpringBootProject.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findUserByusername(String username);
}
