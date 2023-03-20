package com.java.SpringBootProject.Controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.SpringBootProject.Entity.User;
import com.java.SpringBootProject.Model.CommentDTO;
import com.java.SpringBootProject.Model.ProductDTO;
import com.java.SpringBootProject.Model.UserDTO;
import com.java.SpringBootProject.Service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/writeComment")
	public String writeComment(HttpSession session, Model model, 
			@RequestParam(name = "content") String content)
	{
		ProductDTO productDTO = (ProductDTO) session.getAttribute("product");
		UserDTO userDTO = new UserDTO();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setRole(user.getRole());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setGender(user.getGender());
		}
		String idPro = String.valueOf(productDTO.getId());
		
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setProductDTO(productDTO);
		commentDTO.setUserDTO(userDTO);
		commentDTO.setContent(content);
		commentDTO.setDateComment(String.valueOf(LocalDate.now()));
		commentDTO.setLikeNumber(0);
	
		commentService.addComment(commentDTO);
		return "redirect:/home/seeProduct?idPro=" + idPro;
	}
}
