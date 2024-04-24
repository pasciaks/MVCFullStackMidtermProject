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

public class CertificationTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Certification certification = null;

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
		certification = em.find(Certification.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		certification = null;
	}

	@Test
	void test_Certification_entity_mapping() {
		assertNotNull(certification);
		assertEquals(1, certification.getId());
	}
	
	@Test
	void test_Certification_description_mapping() {
		assertNotNull(certification);
		assertEquals("Medical", certification.getDescription());
	}
	
	@Test
	void test_Certification_required_mapping() {
		assertNotNull(certification);
		assertTrue(certification.getRequired());
	}
	
	@Test
	void test_Certification_has_certification_mapping() {
		assertNotNull(certification);
		assertNotNull(certification.getPilotCertifications());
		assertTrue(certification.getPilotCertifications().size()>0);
	}

	
}
