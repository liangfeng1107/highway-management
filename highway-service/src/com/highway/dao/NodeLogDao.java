package com.highway.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.highway.model.NodeLog;

public class NodeLogDao {
	
	@SuppressWarnings("unchecked")
	public List<NodeLog> getNodeLog() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from NodeLog order by id desc");
		query.setMaxResults(20);  //查询出来的记录数
		List<NodeLog> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list;
		}
	}
	
	public void addNodeLog(String time, String nodename, String result) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		NodeLog nodelog = new NodeLog(time,nodename,result);
		session.save(nodelog);
		tran.commit();
//		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public NodeLog getNodeLogById(int id) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from NodeLog as log where log.id=:sid");
		query.setInteger("sid", id);
		List<NodeLog> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
