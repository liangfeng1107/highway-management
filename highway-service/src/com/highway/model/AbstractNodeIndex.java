package com.highway.model;

/**
 * AbstractNodeIndex entity provides the base persistence definition of the
 * NodeIndex entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNodeIndex implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nodeName;
	private Integer RIndex;
	private Integer AIndex;

	// Constructors

	/** default constructor */
	public AbstractNodeIndex() {
	}

	/** full constructor */
	public AbstractNodeIndex(String nodeName, Integer RIndex, Integer AIndex) {
		this.nodeName = nodeName;
		this.RIndex = RIndex;
		this.AIndex = AIndex;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Integer getRIndex() {
		return this.RIndex;
	}

	public void setRIndex(Integer RIndex) {
		this.RIndex = RIndex;
	}

	public Integer getAIndex() {
		return this.AIndex;
	}

	public void setAIndex(Integer AIndex) {
		this.AIndex = AIndex;
	}

}