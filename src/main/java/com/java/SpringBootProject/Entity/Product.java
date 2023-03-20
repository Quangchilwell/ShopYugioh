package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Data
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	@Column(name = "id_Cate")
	private int idCate;

	@Column(name = "image_url")
	private String imageUrl;

	@Lob
	private String name;

	@Column(name = "price")
	private float price;

	@Column(name = "quantity")
	private int quantity;

	
}