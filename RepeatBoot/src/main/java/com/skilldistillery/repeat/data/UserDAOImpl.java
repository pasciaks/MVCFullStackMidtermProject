package com.skilldistillery.repeat.data;

import java.util.List;

import org.springframework.stereotype.Service;

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

		List<User> users  = em.createQuery(jpql, User.class).setParameter("username", username).setParameter("password", password).getResultList();
		
		if (!users.isEmpty() ) {
			authenticatedUser = users.get(0);
		}
		
		return authenticatedUser;
	}
	
}
