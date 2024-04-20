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

public class OrganizationTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Organization organization = null;

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
		organization = em.find(Organization.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		organization = null;
	}

	@Test
	void test_Organization_entity_mapping() {
		assertNotNull(organization);
		assertEquals(1, organization.getId());
	}
	

	@Test
	void test_Organization_name_mapping() {
		assertNotNull(organization);
		assertEquals("1-501AB", organization.getName());
	}
	
	@Test
	void test_Organization_enabled_mapping() {
		assertNotNull(organization);
		assertTrue(organization.getEnabled());
	}
	@Test
	void test_Organization_has_Aircraft_mapping() {
		assertNotNull(organization);
		assertTrue(organization.getAircrafts().size()>0);
	}

	
}
