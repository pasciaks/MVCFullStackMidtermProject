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

public class PilotCertificationTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private PilotCertification pilotCertification = null;

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
		pilotCertification = em.find(PilotCertification.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pilotCertification = null;
	}

	@Test
	void test_PilotCertification_entity_mapping() {
		assertNotNull(pilotCertification);
		assertEquals(1, pilotCertification.getId());
	}
	
	@Test
	void test_PilotCertification_name_mapping() {
		assertNotNull(pilotCertification);
		
		System.out.println(pilotCertification.getEffectiveDate());
		assertEquals(2023, pilotCertification.getEffectiveDate().getYear());
		assertEquals(1, pilotCertification.getEffectiveDate().getMonthValue());
		assertEquals(1, pilotCertification.getEffectiveDate().getDayOfMonth());
	}
	
	@Test	
	void test_PilotCertification_details_mapping() {
		assertNotNull(pilotCertification);
		assertEquals("flight physical short", pilotCertification.getDetails());
	}

	@Test
	void test_PilotCertification_passed_mapping() {
		assertNotNull(pilotCertification);
		assertTrue(pilotCertification.getPassed());
	}
	

    @Test
	void test_PilotCertification_expirationDate_mapping() {
		assertNotNull(pilotCertification);
		assertNull(pilotCertification.getExpirationDate());
	}

	
}
