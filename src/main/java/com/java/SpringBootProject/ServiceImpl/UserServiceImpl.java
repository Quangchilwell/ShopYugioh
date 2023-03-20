package com.java.SpringBootProject.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.UserDao;
import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void addUser(UserDTO userDTO) {
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		user.setPhone(userDTO.getPhone());
		user.setAvatar(userDTO.getAvatar());
		user.setGender(userDTO.getGender());
		
		userDao.addUser(user);
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		User user = userDao.getUserByID(userDTO.getId());
		if(user != null)
		{
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());
			user.setPhone(userDTO.getPhone());
			user.setRole(userDTO.getRole());
			user.setAvatar(userDTO.getAvatar());
			user.setGender(userDTO.getGender());
			
			userDao.updateUser(user);
		}
		
	}

	@Override
	public void deleteUser(int id) {
		User user = userDao.getUserByID(id);
		if(user != null)
		{
			userDao.deleteUser(user);
		}
		
	}

	@Override
	public UserDTO getUserByID(int id) {
		UserDTO userDTO = new UserDTO();
		User user = userDao.getUserByID(id);
		
		if(user != null)
		{
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
			return userDTO;
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User> users = userDao.getAllUser();
		ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for(User user : users)
		{
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		User user = userDao.getUserByUsername(username);
		
		if(user != null)
		{
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
			return userDTO;
		}
		return null;
	}
	
}
