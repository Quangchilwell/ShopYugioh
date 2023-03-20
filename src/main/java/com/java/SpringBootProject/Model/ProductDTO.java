package com.java.SpringBootProject.Model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {
	private int id;
	private String name;
	private float price;
	private int quantity;
	private String description;
	private String imageUrl;
	private MultipartFile file;
	private int idCate;
	private CategoryDTO categoryDTO;
}
