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

public class PilotLogEntryTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private PilotLogEntry pilotLogEntry = null;

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
		pilotLogEntry = em.find(PilotLogEntry.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pilotLogEntry = null;
	}

	@Test
	void test_PilotLogEntry_entity_mapping() {
		assertNotNull(pilotLogEntry);
		assertEquals(1, pilotLogEntry.getId());
	}
	
	@Test
	void test_PilotLogEntry_start_time_mapping() {
		assertNotNull(pilotLogEntry);
		assertEquals(2024, pilotLogEntry.getStartTime().getYear());
		assertEquals(4, pilotLogEntry.getStartTime().getMonthValue());
		assertEquals(19, pilotLogEntry.getStartTime().getDayOfMonth());
	}
	
	@Test
	void test_PilotLogEntry_stop_time_mapping() {
		assertNotNull(pilotLogEntry);
		assertNull(pilotLogEntry.getStopTime());
	}
	
	@Test	
	void test_PilotLogEntry_created_at_mapping() {
		assertNotNull(pilotLogEntry);
		assertNull(pilotLogEntry.getCreatedAt());
	}
	
	
	@Test	
	void test_PilotLogEntry_has_user_at_mapping() {
		assertNotNull(pilotLogEntry);
		System.out.println(pilotLogEntry);
		assertNotNull(pilotLogEntry.getUser());
		System.out.println(pilotLogEntry.getUser().getUsername());
	}
	
	@Test	
	void test_PilotLogEntry_has_experience_type() {
		assertNotNull(pilotLogEntry);
		assertNotNull(pilotLogEntry.getExperienceType());
		System.out.println(pilotLogEntry.getExperienceType().getDescription());
		assertEquals("Day flight minutes", pilotLogEntry.getExperienceType().getDescription());
	}
	
}
