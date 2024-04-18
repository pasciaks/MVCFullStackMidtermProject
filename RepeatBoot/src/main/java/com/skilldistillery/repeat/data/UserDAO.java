package com.skilldistillery.repeat.data;

import com.skilldistillery.repeat.entities.User;

public interface UserDAO {
	
	User authenticateUser(String username, String password);

}
