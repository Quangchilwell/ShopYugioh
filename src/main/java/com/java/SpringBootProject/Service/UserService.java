package com.java.SpringBootProject.Service;

import java.util.List;
import com.java.SpringBootProject.Model.UserDTO;

public interface UserService {
	public void addUser(UserDTO userDTO);
	
	public void updateUser(UserDTO userDTO);
	
	public void deleteUser(int id);
	
	public UserDTO getUserByID(int id);
	
	public UserDTO getUserByUsername(String username);
	
	public List<UserDTO> getAllUser();
}
