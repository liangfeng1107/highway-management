package com.highway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.highway.model.EquipmentTable;

public class EquipmentTableDao {
	
	@SuppressWarnings("unchecked")
	public List<EquipmentTable> getAllEquipment() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from EquipmentTable");
		List<EquipmentTable> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list;
		}
	}
	
	public int setEquipmnetState(int id, String state) {
		int ret = -1;
		if(getEquipmentTableById(id) == null) {
			System.out.println("无该设备！");
			return ret;
		} else {
			Session session = new Configuration().configure().buildSessionFactory().openSession();
			Transaction trans=session.beginTransaction();
			String hql="update EquipmentTable as equip set equip.state=:ustate where equip.id=:uid";
			Query queryupdate=session.createQuery(hql);
			queryupdate.setString("ustate", state);
			queryupdate.setInteger("uid", id);
			ret = queryupdate.executeUpdate();
			trans.commit();
			return ret;
		}
	}
	
	@SuppressWarnings("unchecked")
	public EquipmentTable getEquipmentTableById(int id) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from EquipmentTable as equip where equip.id=:sid");
		query.setInteger("sid", id);
		List<EquipmentTable> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
