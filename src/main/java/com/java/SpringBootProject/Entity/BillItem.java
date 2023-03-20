package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the billitem database table.
 * 
 */
@Entity
@Data
@Table(name = "billitem")
public class BillItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_bill")
	private int idBill;

	@Column(name="id_pro")
	private int idPro;

	private int quantity;

	@Column(name="unit_price")
	private float unitPrice;
	
//	@ManyToOne
//	@JoinColumn(name = "id_bill")
//	Bill bill;

}