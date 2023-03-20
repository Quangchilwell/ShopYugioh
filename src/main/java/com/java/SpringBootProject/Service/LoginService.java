package com.java.SpringBootProject.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.SpringBootProject.Dao.UserDao;

@Service
@Transactional
public class LoginService implements UserDetailsService{
	@Autowired
	UserDao userDao;

	@Autowired
	HttpSession session;
	
	public void UserSession(com.java.SpringBootProject.Entity.User user)
	{
		session.setAttribute("user", user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.java.SpringBootProject.Entity.User user = userDao.getUserByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("no user");
		}
		
		UserSession(user);
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
	
		UserDetails details = new User(user.getUsername(), "{noop}" + user.getPassword(), true, true, true, true, authorities);
		return details;
	}
	
	
}
