package com.skilldistillery.repeat.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RoleTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Role role = null;

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
		role = em.find(Role.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		role = null;
	}

	@Test
	void test_Role_entity_mapping() {
		assertNotNull(role);
		assertEquals(1, role.getId());
	}
	
	@Test
	void test_Role_name_mapping() {
		assertNotNull(role);
		assertEquals("pilot", role.getName());
	}
	
	
}
