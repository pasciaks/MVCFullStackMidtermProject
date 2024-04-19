package com.skilldistillery.repeat.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private User user = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("RepeatJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		
	}
	@Test
	void test_User_entity_username() {
		assertNotNull(user);
		assertEquals("maverick",user.getUsername() );
	}
	@Test
	void test_User_entity_password() {
		assertNotNull(user);
		assertEquals("topgun",user.getPassword() );
	}
	@Test
	void test_User_entity_date_of_birth() {
		assertNotNull(user);
		System.out.println(user.getDateOfBirth());
	}
	@Test
	void test_User_entity_image_url() {
		assertNotNull(user);
		assertEquals("https://m.media-amazon.com/images/M/MV5BYWI1ZDQ4ZDItNjk0Ny00ZDcyLWI5MjctMmFkZjdkODI5ZGRlXkEyXkFqcGdeQWRvb2xpbmhk._V1_.jpg", user.getImageUrl());
	}
	@Test
	void test_User_entity_date_of_enabled() {
		assertNotNull(user);
		assertTrue(user.getEnabled());
	}
	
}
