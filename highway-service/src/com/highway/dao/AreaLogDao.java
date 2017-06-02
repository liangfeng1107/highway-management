package com.highway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.highway.model.AreaLog;

public class AreaLogDao {
	
	@SuppressWarnings("unchecked")
	public List<AreaLog> getAreaLogList(){
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from AreaLog");
		List<AreaLog> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list;
		}
	}
}
