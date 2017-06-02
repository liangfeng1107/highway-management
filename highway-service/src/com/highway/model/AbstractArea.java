package com.highway.model;

/**
 * AbstractArea entity provides the base persistence definition of the Area
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private String areaName;
	private String roadName;
	private Integer RIndex;
	private Integer baudrate;
	private String port;

	// Constructors

	/** default constructor */
	public AbstractArea() {
	}

	/** minimal constructor */
	public AbstractArea(String areaName, String roadName, Integer RIndex) {
		this.areaName = areaName;
		this.roadName = roadName;
		this.RIndex = RIndex;
	}

	/** full constructor */
	public AbstractArea(String areaName, String roadName, Integer RIndex,
			Integer baudrate, String port) {
		this.areaName = areaName;
		this.roadName = roadName;
		this.RIndex = RIndex;
		this.baudrate = baudrate;
		this.port = port;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRoadName() {
		return this.roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public Integer getRIndex() {
		return this.RIndex;
	}

	public void setRIndex(Integer RIndex) {
		this.RIndex = RIndex;
	}

	public Integer getBaudrate() {
		return this.baudrate;
	}

	public void setBaudrate(Integer baudrate) {
		this.baudrate = baudrate;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}