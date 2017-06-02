package com.highway.model;

/**
 * EquipmentTable entity. @author MyEclipse Persistence Tools
 */
public class EquipmentTable extends AbstractEquipmentTable implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public EquipmentTable() {
	}

	/** minimal constructor */
	public EquipmentTable(String px, String py, String commState, String type,
			String area, String TSurvnodename, String TSurvnoderoad,
			Integer survnodeindex, String baudrate, String port) {
		super(px, py, commState, type, area, TSurvnodename, TSurvnoderoad,
				survnodeindex, baudrate, port);
	}

	/** full constructor */
	public EquipmentTable(String px, String py, String address,
			String commState, String batteryState, String state, String type,
			String area, String TSurvnodename, String TSurvnoderoad,
			Integer survnodeindex, String baudrate, String port) {
		super(px, py, address, commState, batteryState, state, type, area,
				TSurvnodename, TSurvnoderoad, survnodeindex, baudrate, port);
	}

}
