package com.highway.service;

import com.highway.dao.*;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.highway.model.EquipmentTable;
import com.highway.model.NodeLog;

@javax.jws.WebService(targetNamespace = "http://service.highway.com/", serviceName = "NodeLogService", portName = "NodeLogService")
public class NodeLogService {

	com.highway.dao.NodeLogDao nodeLogDao = new com.highway.dao.NodeLogDao();

	public List<NodeLog> getNodeLog() {
		return nodeLogDao.getNodeLog();
	}

	public void addNodeLog(String time, String nodename, String result) {
		nodeLogDao.addNodeLog(time, nodename, result);
	}

	public NodeLog getNodeLogById(int id) {
		return nodeLogDao.getNodeLogById(id);
	}

}