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

public class AircraftTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private Aircraft aircraft = null;

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
		aircraft = em.find(Aircraft.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		aircraft = null;
	}

	@Test
	void test_Aircraft_entity_mapping() {
		assertNotNull(aircraft);
	}
	
	@Test
	void test_Aircraft_tail_number_mapping() {
		assertNotNull(aircraft);
		assertEquals("T1679", aircraft.getTailNumber());
	}
	
	@Test
	void test_Aircraft_active_mapping() {
		assertNotNull(aircraft);
		assertTrue(aircraft.getActive());
	}
	
	@Test
	void test_Aircraft_enabled_mapping() {
		assertNotNull(aircraft);
		assertNull(aircraft.getEnabled());
	}
	@Test
	void test_Aircraft_has_AircraftType() {
		assertNotNull(aircraft);
		assertEquals(1,aircraft.getAircraftType().getId());
		assertEquals("ah64d",aircraft.getAircraftType().getAircraftType());
	}


	
}
