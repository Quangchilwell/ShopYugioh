package com.java.SpringBootProject.Model;

import lombok.Data;

@Data
public class BillDTO {
	private int id;
	private String billDate;
	private float totalPrice;
	private int totalProduct;
	private int idUser;
	private UserDTO user;
}
