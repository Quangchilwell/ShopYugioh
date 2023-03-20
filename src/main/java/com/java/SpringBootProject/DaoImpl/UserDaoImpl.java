package com.java.SpringBootProject.DaoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.UserDao;
import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Repository.UserRepository;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User getUserByID(int id) {
		return userRepository.getById(id);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findUserByusername(username);
	}

}
