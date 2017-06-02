package com.highway.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.highway.model.User;

@WebService
public interface MyUserService {
    public void add(@WebParam(name = "user") User user);
    public int addUser(@WebParam(name = "username")String username,
            @WebParam(name = "password")String password, 
            @WebParam(name = "email")String email);
    public void delete(@WebParam(name = "user") User user);
    public int login(@WebParam(name = "username")String username,
        @WebParam(name = "password")String password);
    public User getUser(@WebParam(name = "username")String username);
}
