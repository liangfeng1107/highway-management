package com.highway.model;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
public class Area extends AbstractArea implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String areaName, String roadName, Integer RIndex) {
		super(areaName, roadName, RIndex);
	}

	/** full constructor */
	public Area(String areaName, String roadName, Integer RIndex,
			Integer baudrate, String port) {
		super(areaName, roadName, RIndex, baudrate, port);
	}

}
