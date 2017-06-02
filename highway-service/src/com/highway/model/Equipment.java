package com.highway.model;

import java.sql.Timestamp;

/**
 * Equipment entity. @author MyEclipse Persistence Tools
 */
public class Equipment extends AbstractEquipment implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Equipment() {
	}

	/** minimal constructor */
	public Equipment(Integer id, Integer number, String name, String type,
			String adress) {
		super(id, number, name, type, adress);
	}

	/** full constructor */
	public Equipment(Integer id, Integer number, String name, String type,
			String adress, String state, Timestamp settime, String use) {
		super(id, number, name, type, adress, state, settime, use);
	}

}
