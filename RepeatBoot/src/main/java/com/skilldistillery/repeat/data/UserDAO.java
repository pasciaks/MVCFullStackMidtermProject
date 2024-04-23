package com.skilldistillery.repeat.data;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.repeat.entities.Organization;
import com.skilldistillery.repeat.entities.Role;
import com.skilldistillery.repeat.entities.User;

public interface UserDAO {

	User authenticateUser(String username, String password);

	User registerUser(String username, String password, String imageUrl, String roleId, String organizationId,
			LocalDate dateOfBirth);

	List<User> findAllUser();

	User enableUser(int id);

	User disableUser(int id);

	User updateUser(int id, User user);

	User findById(int id);

	List<Role> findAllRoles();

	List<Organization> findAllOrganizations();

}
