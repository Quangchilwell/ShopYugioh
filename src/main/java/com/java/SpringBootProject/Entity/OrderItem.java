package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the orderitem database table.
 * 
 */
@Entity
@Data
@Table(name="orderitem")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_order")
	private int idOrder;

	@Column(name="id_pro")
	private int idPro;

	private int quantity;

	@Column(name="unit_price")
	private float unitPrice;
}