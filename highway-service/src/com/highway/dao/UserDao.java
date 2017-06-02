package com.highway.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.highway.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class UserDao{

	@SuppressWarnings("unchecked")
	public int login(String username, String password) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from User where username=:sname and password=:spwd");
		query.setString("sname", username);
		query.setString("spwd", password);
		List<User> list = query.list();
		if(list.isEmpty()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public int addUser(String name, String pwd, String email) {
		Date date = new Date();       
		Timestamp createTime = new Timestamp(date.getTime());
		User user2 = new User(name, email, pwd, createTime, 1);
		if(selectUser(name) != null) {
			return 0;
		} else {
			createUser(user2);
		}
		return 1;
	}
	public void createUser(User user) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.save(user);
		tran.commit();
		session.close();
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		session.delete(user);
		tran.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public User selectUser(String username) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Query query = session.createQuery("from User where username=:sname");
		query.setString("sname", username);
		List<User> list = query.list();
		if(list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
