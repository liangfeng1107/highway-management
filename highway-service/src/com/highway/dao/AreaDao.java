package com.highway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.highway.model.Area;


public class AreaDao {
	
	@SuppressWarnings("unchecked")
	public Area getAreaById(int id) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from Area where id=:sid");
		query.setInteger("sid", id);
		List<Area> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
