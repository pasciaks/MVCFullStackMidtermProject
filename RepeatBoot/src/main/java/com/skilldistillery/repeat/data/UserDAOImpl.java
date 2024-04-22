package com.skilldistillery.repeat.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.repeat.entities.Organization;
import com.skilldistillery.repeat.entities.Role;
import com.skilldistillery.repeat.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User authenticateUser(String username, String password) {

		User authenticatedUser = null;

		String jpql = "SELECT user FROM User user WHERE user.username = :username AND user.password = :password AND user.enabled = true";

		List<User> users = em.createQuery(jpql, User.class).setParameter("username", username)
				.setParameter("password", password).getResultList();

		if (!users.isEmpty()) {
			authenticatedUser = users.get(0);
		}

		return authenticatedUser;
	}

	@Override
	public User registerUser(String username, String password, String imageUrl, String roleId, String organizationId,
			LocalDate dateOfBirth) {

		User user = new User();

		user.setUsername(username);
		user.setPassword(password);
		user.setImageUrl(imageUrl);
		user.setDateOfBirth(dateOfBirth);
		
		user.setEnabled(false);

		// role - find role according to roleId passed in

		Role role = em.find(Role.class, roleId);

		user.setRole(role);

		// organization - find organization according to organizationId passed in

		Organization organization = em.find(Organization.class, organizationId);

		user.setOrganization(organization);
		
		try {
			em.persist(user);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}

		return user;

	}

}
