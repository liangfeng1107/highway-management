package com.highway.model;

/**
 * AbstractAreaLog entity provides the base persistence definition of the
 * AreaLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAreaLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String time;
	private String areaName;
	private String result;

	// Constructors

	/** default constructor */
	public AbstractAreaLog() {
	}

	/** minimal constructor */
	public AbstractAreaLog(String result) {
		this.result = result;
	}

	/** full constructor */
	public AbstractAreaLog(String time, String areaName, String result) {
		this.time = time;
		this.areaName = areaName;
		this.result = result;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}