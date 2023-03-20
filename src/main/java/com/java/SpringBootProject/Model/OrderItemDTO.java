package com.java.SpringBootProject.Model;

import lombok.Data;

@Data
public class OrderItemDTO {
	private int id;
	private OrderDTO orderDTO;
	private int idOrder;
	private ProductDTO productDTO;
	private int idProduct;
	private int quantity;
	private float unitPrice;
}
