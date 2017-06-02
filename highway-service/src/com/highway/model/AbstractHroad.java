package com.highway.model;

/**
 * AbstractHroad entity provides the base persistence definition of the Hroad
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractHroad implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hroadName;

	// Constructors

	/** default constructor */
	public AbstractHroad() {
	}

	/** full constructor */
	public AbstractHroad(String hroadName) {
		this.hroadName = hroadName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHroadName() {
		return this.hroadName;
	}

	public void setHroadName(String hroadName) {
		this.hroadName = hroadName;
	}

}