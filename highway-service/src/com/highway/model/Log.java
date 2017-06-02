package com.highway.model;

import java.sql.Timestamp;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */
public class Log extends AbstractLog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** full constructor */
	public Log(String content, Timestamp time, String username) {
		super(content, time, username);
	}

}
