package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Data
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="date_comment")
	private Date dateComment;

	@Column(name="id_product")
	private int idProduct;

	@Column(name="id_user")
	private int idUser;

	@Column(name="like_number")
	private int likeNumber;

}