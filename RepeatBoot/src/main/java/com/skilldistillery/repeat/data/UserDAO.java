package com.skilldistillery.repeat.data;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.repeat.entities.User;

public interface UserDAO {

	User authenticateUser(String username, String password);

	User registerUser(String username, String password, String imageUrl, String roleId, String organizationId,
			LocalDate dateOfBirth);

	List<User> findAllUser();

}
