package com.highway.service;


import java.sql.Timestamp;
import java.util.Date;

import javax.jws.WebService;

import com.highway.dao.UserDao;
import com.highway.model.User;


@WebService(endpointInterface = "com.highway.service.MyUserService",
portName="UserServicePort",
serviceName="UserService",
targetNamespace="http://service.highway.com/")
public class MyUserServiceImpl implements MyUserService {
public UserDao userDao = new UserDao();

public UserDao getUserDao() {
return userDao;	
}

public void setUserDao(UserDao userDao) {
this.userDao = userDao;
}

public int login(String username, String password) {
System.out.println("[Service:]Login(Method) is running@!");
return userDao.login(username, password);
}

public void add(User user) {	
System.out.println("[Service:]CreateUser(Method) is running@!");
userDao.createUser(user);
}


public void delete(User user) {
System.out.println("[Service:]Delete(Method) is running@!");
userDao.deleteUser(user);
}

public User getUser(String username) {
	// TODO Auto-generated method stub
	if(userDao.selectUser(username) != null) {
		return userDao.selectUser(username);
	} else {
		return null;
	}
	
}

public int addUser(String username, String password, String email) {
	// TODO Auto-generated method stub
	Date date = new Date();       
	Timestamp createTime = new Timestamp(date.getTime());
	User user = new User(username, email, password, createTime, 1);
	if(getUser(username) != null) {
		return 0;
	} else {
		add(user);
	}
	return 1;
}

}

