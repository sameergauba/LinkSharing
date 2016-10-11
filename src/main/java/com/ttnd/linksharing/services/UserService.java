package com.ttnd.linksharing.services;

import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.User;

public interface UserService {
	
	public Long saveUser(UserDTO userdto);
	public UserDTO getUser(String username, String password);
	public UserDTO verifyUser(String userName);

}
