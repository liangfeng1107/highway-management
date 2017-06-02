package com.highway.model;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, Integer authority) {
		super(username, password, authority);
	}

	/** full constructor */
	public User(String username, String email, String password,
			Timestamp createTime, Integer authority) {
		super(username, email, password, createTime, authority);
	}

}
