package com.highway.model;

import java.sql.Timestamp;

/**
 * AbstractEquipment entity provides the base persistence definition of the
 * Equipment entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractEquipment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer number;
	private String name;
	private String type;
	private String adress;
	private String state;
	private Timestamp settime;
	private String use;

	// Constructors

	/** default constructor */
	public AbstractEquipment() {
	}

	/** minimal constructor */
	public AbstractEquipment(Integer id, Integer number, String name,
			String type, String adress) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.type = type;
		this.adress = adress;
	}

	/** full constructor */
	public AbstractEquipment(Integer id, Integer number, String name,
			String type, String adress, String state, Timestamp settime,
			String use) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.type = type;
		this.adress = adress;
		this.state = state;
		this.settime = settime;
		this.use = use;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getSettime() {
		return this.settime;
	}

	public void setSettime(Timestamp settime) {
		this.settime = settime;
	}

	public String getUse() {
		return this.use;
	}

	public void setUse(String use) {
		this.use = use;
	}

}