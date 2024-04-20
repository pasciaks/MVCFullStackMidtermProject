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

public class ExperienceTypeRequirementTest {

	private static EntityManagerFactory emf;

	private EntityManager em;

	private ExperienceTypeRequirement experienceTypeRequirement = null;

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
		experienceTypeRequirement = em.find(ExperienceTypeRequirement.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		experienceTypeRequirement = null;
	}

	@Test
	void test_ExperienceTypeRequirement_entity_mapping() {
		assertNotNull(experienceTypeRequirement);
	}

	@Test
	void test_ExperienceTypeRequirement_minutes_required_mapping() {
		assertNotNull(experienceTypeRequirement);
		assertEquals(60, experienceTypeRequirement.getMinutesRequired());
	}

	@Test
	void test_ExperienceTypeRequirement_has_AircraftType() {
		assertNotNull(experienceTypeRequirement);
		assertEquals(1, experienceTypeRequirement.getAircraftType().getId());
		assertTrue(experienceTypeRequirement.getAircraftType().getEnabled());
		assertNull(experienceTypeRequirement.getAircraftType().getImageUrl());
	}

	@Test
	void test_ExperienceTypeRequirement_has_Experience_Types() {
		assertNotNull(experienceTypeRequirement);
		assertNotNull(experienceTypeRequirement.getExperienceTypes());
		assertTrue(experienceTypeRequirement.getExperienceTypes().size() > 0);
	}

}
