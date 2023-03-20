package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the order_customer database table.
 * 
 */
@Entity
@Table(name="order_customer")
@Data
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String address;

	@Column(name="id_user")
	private int idUser;

	@Column(name="total_product")
	private int totalProduct;
	
	@Column(name="total_price")
	private float totalPrice;
	
	@Lob
	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="date_order")
	private Date dateOrder;
	
	@Column(name="index_extra")
	private int indexExtra;
	
}