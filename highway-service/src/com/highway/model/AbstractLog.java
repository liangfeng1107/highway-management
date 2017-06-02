package com.highway.model;

import java.sql.Timestamp;

/**
 * AbstractLog entity provides the base persistence definition of the Log
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Timestamp time;
	private String username;

	// Constructors

	/** default constructor */
	public AbstractLog() {
	}

	/** full constructor */
	public AbstractLog(String content, Timestamp time, String username) {
		this.content = content;
		this.time = time;
		this.username = username;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}