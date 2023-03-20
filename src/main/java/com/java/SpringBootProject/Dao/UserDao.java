package com.java.SpringBootProject.Dao;

import java.util.List;

import com.java.SpringBootProject.Entity.User;

public interface UserDao {
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public User getUserByID(int id);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUser();
	
	
}
