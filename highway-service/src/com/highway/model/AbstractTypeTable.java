package com.highway.model;

/**
 * AbstractTypeTable entity provides the base persistence definition of the
 * TypeTable entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTypeTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typename;

	// Constructors

	/** default constructor */
	public AbstractTypeTable() {
	}

	/** full constructor */
	public AbstractTypeTable(String typename) {
		this.typename = typename;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}