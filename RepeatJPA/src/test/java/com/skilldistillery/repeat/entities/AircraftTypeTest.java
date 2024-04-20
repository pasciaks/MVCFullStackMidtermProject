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

public class AircraftTypeTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private AircraftType aircraftType = null;

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
		aircraftType = em.find(AircraftType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		aircraftType = null;
	}

	@Test
	void test_AircraftType_entity_mapping() {
		assertNotNull(aircraftType);
		assertEquals(1, aircraftType.getId());
	}
	
	@Test
	void test_AircraftType_has_Aircrafts_mapping() {
		assertNotNull(aircraftType);
        assertTrue(aircraftType.getAircrafts().size()> 0);
    }
//	@Test
//	void test_AircraftType_enabled_mapping() {
//		assertNotNull(aircraftType);
//		assertTrue(aircraftType.getEnabled());
//	}
	
	
}
