package com.skilldistillery.repeat.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.repeat.entities.ExperienceType;
import com.skilldistillery.repeat.entities.PilotLogEntry;
import com.skilldistillery.repeat.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PilotDAOImpl implements PilotDAO {

	@PersistenceContext
	private EntityManager em;

	public PilotLogEntry addPilotLog(LocalDateTime startTime, LocalDateTime stopTime, int experienceTypeId,
			int userId) {
		PilotLogEntry pilotLog = new PilotLogEntry();
		pilotLog.setStartTime(startTime);
		pilotLog.setStopTime(stopTime);
		ExperienceType experienceType = em.find(ExperienceType.class, experienceTypeId);
		pilotLog.setExperienceType(experienceType);
		User user = em.find(User.class, userId);
		pilotLog.setUser(user);
		em.persist(pilotLog);
		em.flush();

		return pilotLog;
	}

	@Override
	public List<ExperienceType> findAllExperienceTypes() {
		String jpql = "SELECT et FROM ExperienceType et";
		List<ExperienceType> experienceTypes = new ArrayList<>();
		experienceTypes = em.createQuery(jpql, ExperienceType.class).getResultList();
		System.out.println(experienceTypes.size());
		System.out.println(experienceTypes);
		return experienceTypes;
	}

	@Override
	public List<PilotLogEntry> findAllPilotLogEntries(int userId) {
		User user = em.find(User.class, userId);
		return user.getPilotLogEntries();
	}

	@Override
	public PilotLogEntry findPilotLogEntryById(int id) {
		PilotLogEntry pilotLogEntry = em.find(PilotLogEntry.class, id);
		return pilotLogEntry;
	}

	@Override
	public PilotLogEntry updatePilotLog(int id, PilotLogEntry pilotLogEntry) {

		PilotLogEntry managed = em.find(PilotLogEntry.class, id);

		if (managed == null) {
			return null;
		}

		managed.setStartTime(pilotLogEntry.getStartTime());
		managed.setStopTime(pilotLogEntry.getStopTime());
		managed.setUser(pilotLogEntry.getUser());
		managed.setExperienceType(pilotLogEntry.getExperienceType());

		try {
			em.persist(managed);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			managed = null;
		}

		System.out.println(managed);

		return managed;
	
	}

	@Override
	public ExperienceType findById(int id) {
		ExperienceType experienceType = em.find(ExperienceType.class, id);
		return experienceType;
	}
	
}
