package com.java.SpringBootProject.Controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Dao.UserDao;
import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login(Model model ,@RequestParam(name = "e", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		return "account/login";
	}

	@GetMapping("/home/register")
	public String register(Model model , @RequestParam(name = "e", required = false) String error)
	{
		if(error != null) {
			model.addAttribute("error", error);
		}
		model.addAttribute("user", new UserDTO());
		return "account/register";
	}
	
	@PostMapping("/home/register")
	public String register(Model model, @ModelAttribute(name = "user") UserDTO userDTO)
	{
		List<UserDTO> userDTOs = userService.getAllUser();
		boolean check = false;
		for(UserDTO userDTO2: userDTOs)
		{
			if(userDTO2.getUsername().equals(userDTO.getUsername()))
			{
				check = true;
				break;
			}
		}

		if(check == true) {
			return "redirect:/home/register?e=exist";			
		}
		
		userDTO.setRole("ROLE_USER");
		userService.addUser(userDTO);
		return "account/registerSuccess";
	}
	
} 
