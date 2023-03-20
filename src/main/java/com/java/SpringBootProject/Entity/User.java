package com.java.SpringBootProject.Entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String avatar;

	private byte enable;

	@Lob
	private String name;

	@Lob
	private String password;

	@Lob
	private String phone;

	@Lob
	private String role;
	
	@Lob
	private String gender;

	@Lob
	private String username;

}