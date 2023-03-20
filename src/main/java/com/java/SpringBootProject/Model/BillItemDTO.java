package com.java.SpringBootProject.Model;

import lombok.Data;

@Data
public class BillItemDTO {
	private int id;
	private BillDTO billDTO;
	private int idBill;
	private ProductDTO productDTO;
	private int idProduct;
	private int quantity;
	private float unitPrice;
}
