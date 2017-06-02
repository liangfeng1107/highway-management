package com.highway.model;

/**
 * AbstractEquipmentTable entity provides the base persistence definition of the
 * EquipmentTable entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractEquipmentTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private String px;
	private String py;
	private String address;
	private String commState;
	private String batteryState;
	private String state;
	private String type;
	private String area;
	private String TSurvnodename;
	private String TSurvnoderoad;
	private Integer survnodeindex;
	private String baudrate;
	private String port;

	// Constructors

	/** default constructor */
	public AbstractEquipmentTable() {
	}

	/** minimal constructor */
	public AbstractEquipmentTable(String px, String py, String commState,
			String type, String area, String TSurvnodename,
			String TSurvnoderoad, Integer survnodeindex, String baudrate,
			String port) {
		this.px = px;
		this.py = py;
		this.commState = commState;
		this.type = type;
		this.area = area;
		this.TSurvnodename = TSurvnodename;
		this.TSurvnoderoad = TSurvnoderoad;
		this.survnodeindex = survnodeindex;
		this.baudrate = baudrate;
		this.port = port;
	}

	/** full constructor */
	public AbstractEquipmentTable(String px, String py, String address,
			String commState, String batteryState, String state, String type,
			String area, String TSurvnodename, String TSurvnoderoad,
			Integer survnodeindex, String baudrate, String port) {
		this.px = px;
		this.py = py;
		this.address = address;
		this.commState = commState;
		this.batteryState = batteryState;
		this.state = state;
		this.type = type;
		this.area = area;
		this.TSurvnodename = TSurvnodename;
		this.TSurvnoderoad = TSurvnoderoad;
		this.survnodeindex = survnodeindex;
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

	public String getPx() {
		return this.px;
	}

	public void setPx(String px) {
		this.px = px;
	}

	public String getPy() {
		return this.py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommState() {
		return this.commState;
	}

	public void setCommState(String commState) {
		this.commState = commState;
	}

	public String getBatteryState() {
		return this.batteryState;
	}

	public void setBatteryState(String batteryState) {
		this.batteryState = batteryState;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTSurvnodename() {
		return this.TSurvnodename;
	}

	public void setTSurvnodename(String TSurvnodename) {
		this.TSurvnodename = TSurvnodename;
	}

	public String getTSurvnoderoad() {
		return this.TSurvnoderoad;
	}

	public void setTSurvnoderoad(String TSurvnoderoad) {
		this.TSurvnoderoad = TSurvnoderoad;
	}

	public Integer getSurvnodeindex() {
		return this.survnodeindex;
	}

	public void setSurvnodeindex(Integer survnodeindex) {
		this.survnodeindex = survnodeindex;
	}

	public String getBaudrate() {
		return this.baudrate;
	}

	public void setBaudrate(String baudrate) {
		this.baudrate = baudrate;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}