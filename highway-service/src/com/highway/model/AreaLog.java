package com.highway.model;

/**
 * AreaLog entity. @author MyEclipse Persistence Tools
 */
public class AreaLog extends AbstractAreaLog implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public AreaLog() {
	}

	/** minimal constructor */
	public AreaLog(String result) {
		super(result);
	}

	/** full constructor */
	public AreaLog(String time, String areaName, String result) {
		super(time, areaName, result);
	}

}
