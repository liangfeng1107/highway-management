package com.highway.service;

import com.highway.dao.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.highway.model.Area;
import com.highway.model.AreaLog;
import com.highway.model.EquipmentTable;

@javax.jws.WebService(targetNamespace = "http://service.highway.com/", serviceName = "EquipmentTableService", portName = "EquipmentTablePort")
public class EquipmentTableService {

	com.highway.dao.EquipmentTableDao equipmentTableDao = new com.highway.dao.EquipmentTableDao();

	public List<EquipmentTable> getAllEquipment() {
		return equipmentTableDao.getAllEquipment();
	}

	public int setEquipmnetState(int id, String state) {
		return equipmentTableDao.setEquipmnetState(id, state);
	}

	public EquipmentTable getEquipmentTableById(int id) {
		return equipmentTableDao.getEquipmentTableById(id);
	}

}