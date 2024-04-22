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

public class ExperienceTypeTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ExperienceType experienceType = null;

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
		experienceType = em.find(ExperienceType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		experienceType = null;
	}

	@Test
	void test_ExperienceType_entity_mapping() {
		assertNotNull(experienceType);
	}
	
	@Test
	void test_ExperienceType_description_mapping() {
		assertNotNull(experienceType);
		assertEquals("Day flight minutes", experienceType.getDescription());
	}
	
	@Test
	void test_ExperienceType_enabled_mapping() {
		assertNotNull(experienceType);
		assertTrue(experienceType.getEnabled());
	}
	
	@Test
	void test_ExperienceType_has_requirements_mapping() {
		assertNotNull(experienceType);
		assertEquals(1,experienceType.getExperienceTypeRequirement().getId());
		assertEquals(60,experienceType.getExperienceTypeRequirement().getMinutesRequired());
		assertEquals(1,experienceType.getExperienceTypeRequirement().getAircraftType().getId());
	}

	@Test
	void test_ExperienceType_has_pilot_log_entry() {
		assertNotNull(experienceType);
		assertNotNull(experienceType.getPilotLogEntries());		
		assertEquals(1,experienceType.getPilotLogEntries().size());
			}
	
}
