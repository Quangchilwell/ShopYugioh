package com.java.SpringBootProject.Model;

import lombok.Data;

@Data
public class CommentDTO {
	private int id;
	private int idUser;
	private UserDTO userDTO;
	private int idProduct;
	private ProductDTO productDTO;
	private String content;
	private String dateComment;
	private int likeNumber;
}
