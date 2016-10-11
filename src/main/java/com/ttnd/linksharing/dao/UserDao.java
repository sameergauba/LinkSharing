package com.ttnd.linksharing.dao;

import java.util.List;

import com.ttnd.linksharing.entities.User;

public interface UserDao {
	
	public Integer saveUser(User user);
	public Integer editUser(User user);
	public List<?> retrieveAllUsers();
	public User retrieveUser(String userName, String password);
	public void deleteUser(String userName, String password);
	public Boolean verifyUser(String userName);

}
