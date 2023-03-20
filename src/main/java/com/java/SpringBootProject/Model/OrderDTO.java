package com.java.SpringBootProject.Model;

import lombok.Data;

@Data
public class OrderDTO {
	private int id;
	private int idUser;
	private UserDTO userDTO;
	private int totalProduct;
	private float totalPrice;
	private String address;
	private String status;
	private String dateOrder;
	
	private int indexExtra;
}
