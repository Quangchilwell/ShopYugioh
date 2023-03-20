package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NamedQuery(name="Bill.findAll", query="SELECT b FROM Bill b")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="bill_date")
	private Date billDate;

	@Column(name="id_user")
	private int idUser;

	@Column(name="total_product")
	private int totalProduct;
	
	@Column(name="total_price")
	private float totalPrice;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
//	@Column(name = "billitem")
//	private List<BillItem> billitems;

}