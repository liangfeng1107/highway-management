package com.highway.service;

import com.highway.dao.*;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import com.highway.model.Area;
import com.highway.model.User;

@javax.jws.WebService(targetNamespace = "http://service.highway.com/", serviceName = "AreaService", portName = "AreaPort")
public class AreaService {

	com.highway.dao.AreaDao areaDao = new com.highway.dao.AreaDao();

	public Area getAreaById(int id) {
		return areaDao.getAreaById(id);
	}

}