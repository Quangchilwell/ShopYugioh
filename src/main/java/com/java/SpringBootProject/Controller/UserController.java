package com.java.SpringBootProject.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java.SpringBootProject.Dao.UserDao;
import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.OrderDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.OrderService;
import com.java.SpringBootProject.Service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/userList")
	public String userList(Model model)
	{
		List<OrderDTO> orderDTOs = orderService.getAllOrder();
		int orderQuantity = 0;
		for(OrderDTO orderDTO: orderDTOs)
		{
			orderQuantity += 1;
		}
		model.addAttribute("users", userService.getAllUser());
		model.addAttribute("orderQuantity", orderQuantity);
		return "user/userList";
	}
	
	@GetMapping("/inforUser/{id}")
	public String inforUser(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("user", userService.getUserByID(id));
		return "user/inforUser";
	}
	
	@GetMapping("/addUser")
	public String addUser(Model model)
	{
		model.addAttribute("user", new UserDTO());
		return "user/addUser";
	}
	
	@PostMapping("/addUser")
	public String addUser(Model model, @ModelAttribute(name = "user") UserDTO userDTO)
	{
		MultipartFile file = userDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/SpringBootProject/src/main/resources/static/img/avatarUser/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			userDTO.setAvatar(file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userService.addUser(userDTO);
		model.addAttribute("user", userDTO);
		return "user/addUserSuccess";
	}
	
	@GetMapping("/updateUser/{id}")
	public String updateUser(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("user", userService.getUserByID(id));
		return "user/updateUser";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(Model model, @ModelAttribute(name = "user") UserDTO userDTO)
	{
		MultipartFile file = userDTO.getFile();
		try {
			File newFile = new File("E:/SpringBoot/workspace/SpringBootProject/src/main/resources/static/img/avatarUser/" + file.getOriginalFilename());
			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(file.getOriginalFilename() != null) {
			userDTO.setAvatar(file.getOriginalFilename());
		}
		if(file.getOriginalFilename().equals("")) {
			User user = userDao.getUserByID(userDTO.getId());
			userDTO.setAvatar(user.getAvatar());
		}
		userService.updateUser(userDTO);
		return "redirect:/admin/userList";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name = "id") int id)
	{
		model.addAttribute("user", userService.getUserByID(id));
		return "user/deleteUser";
	}
	
	@GetMapping("/deleteUserSuccess")
	public String deleteUserSuccess(@RequestParam(name = "id") int id)
	{
		userService.deleteUser(id);
		return "user/deleteUserSuccess";
	}
}
