package com.java.SpringBootProject.Model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String name;
	private String phone;
	private String gender;
	private String username;
	private String password;
	private String role;
	private String avatar;
	private MultipartFile file;
}
