package com.skilldistillery.repeat.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.repeat.entities.AircraftType;
import com.skilldistillery.repeat.entities.Certification;
import com.skilldistillery.repeat.entities.ExperienceType;
import com.skilldistillery.repeat.entities.PilotCertification;
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
		String jpql = "SELECT et FROM ExperienceType et order by et.id asc";
		List<ExperienceType> experienceTypes = new ArrayList<>();
		experienceTypes = em.createQuery(jpql, ExperienceType.class).getResultList();
		System.out.println(experienceTypes.size());
		System.out.println(experienceTypes);
		return experienceTypes;
	}

	@Override
	public List<AircraftType> findAllAircraftType() {
		String jpql = "SELECT aircraft from AircraftType aircraft order by aircraft.id asc";
		List<AircraftType> aircraftTypes = new ArrayList<>();
		aircraftTypes = em.createQuery(jpql, AircraftType.class).getResultList();
		System.out.println(aircraftTypes.size());
		System.out.println(aircraftTypes);
		return aircraftTypes;
	}

	@Override
	public List<ExperienceType> findAllExperienceTypesByAircraftTypeId(int aircraftTypeId) {
		String jpql = "SELECT et FROM ExperienceType et JOIN FETCH ExperienceTypeRequirement etr ON etr.id = et.experienceTypeRequirement.id WHERE etr.aircraftType.id = :atid order by et.id asc";
		List<ExperienceType> experienceTypes = new ArrayList<>();
		experienceTypes = em.createQuery(jpql, ExperienceType.class).setParameter("atid", aircraftTypeId)
				.getResultList();
		System.out.println(experienceTypes.size());
		System.out.println(experienceTypes);
		return experienceTypes;
	}

	@Override
	public List<PilotLogEntry> findAllPilotLogEntries(int userId) {

		String jpql = "SELECT ple FROM PilotLogEntry ple WHERE ple.user.id = :userId order by ple.id asc";

		List<PilotLogEntry> pilotLogEntries = new ArrayList<>();

		pilotLogEntries = em.createQuery(jpql, PilotLogEntry.class).setParameter("userId", userId).getResultList();

		return pilotLogEntries;

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

		System.out.println("-----------");
		System.out.println(managed.getExperienceType().getId());

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

	@Override
	public List<PilotLogEntry> findAllPilotLogEntries(int userId, LocalDateTime startedAfterDateTime) {
		String jpql = "SELECT ple FROM PilotLogEntry ple WHERE ple.user.id = :userId and ple.startTime >= :startTime order by ple.id asc";

		List<PilotLogEntry> pilotLogEntries = new ArrayList<>();

		pilotLogEntries = em.createQuery(jpql, PilotLogEntry.class).setParameter("userId", userId)
				.setParameter("startTime", startedAfterDateTime).getResultList();

		return pilotLogEntries;
	}

	@Override
	public Boolean deletePilotLogEntryById(int id) {

		Boolean wasDeleted = false;

		PilotLogEntry managed = em.find(PilotLogEntry.class, id);

		if (managed == null) {
			return false;
		}

		try {
			em.remove(managed);
			em.flush();
			wasDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			wasDeleted = false;
		}

		return wasDeleted;
	}

	@Override
	public List<PilotCertification> findAllPilotCertification(int pilotId) {
		String jpql = "SELECT pc FROM PilotCertification pc JOIN Certification cert ON cert.id = pc.certification.id WHERE pc.user.id = :pilotId order by pc.user.id asc";
		List<PilotCertification> pilotCertifiactions = new ArrayList<>();
		pilotCertifiactions = em.createQuery(jpql, PilotCertification.class).setParameter("pilotId", pilotId)
				.getResultList();
		System.out.println(pilotCertifiactions.size());
		System.out.println(pilotCertifiactions);
		return pilotCertifiactions;
	}

	@Override
	public List<Certification> findAllCertification() {
		String jpql = "SELECT cert FROM Certification cert order by cert.id asc";
		List<Certification> certifications = new ArrayList<>();
		certifications = em.createQuery(jpql, Certification.class).getResultList();
		System.out.println(certifications.size());
		System.out.println(certifications);
		return certifications;
	}

	@Override
	public PilotCertification addPilotCertification(PilotCertification pilotCertification) {

		PilotCertification newManagedPilotCertification = new PilotCertification();

		newManagedPilotCertification.setCertification(pilotCertification.getCertification());
		newManagedPilotCertification.setDetails(pilotCertification.getDetails());
		newManagedPilotCertification.setExpirationDate(pilotCertification.getExpirationDate());
		newManagedPilotCertification.setEffectiveDate(pilotCertification.getEffectiveDate());
		newManagedPilotCertification.setPassed(pilotCertification.getPassed());
		newManagedPilotCertification.setUser(pilotCertification.getUser());
		newManagedPilotCertification.setCertification(pilotCertification.getCertification());

		em.persist(newManagedPilotCertification);

		em.flush();

		return newManagedPilotCertification;
	}

	@Override
	public Certification findCertificationById(int id) {
		Certification certification = em.find(Certification.class, id);
		return certification;
	}

	@Override
	public Boolean deletePilotCertificationById(int id) {
		Boolean wasDeleted = false;

		PilotCertification managed = em.find(PilotCertification.class, id);

		if (managed == null) {
			return false;
		}

		try {
			em.remove(managed);
			em.flush();
			wasDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
			wasDeleted = false;
		}

		return wasDeleted;
	}

	@Override
	public PilotCertification findPilotCertificationById(int id) {
		PilotCertification pilotCertification = em.find(PilotCertification.class, id);
		return pilotCertification;
	}

	@Override
	public PilotCertification updatePilotCertification(int certId, PilotCertification pilotCertification) {
		
		PilotCertification managed = em.find(PilotCertification.class, certId);
		
		if (managed == null) {
			return null;
		}

		managed.setCertification(pilotCertification.getCertification());
		managed.setDetails(pilotCertification.getDetails());
		managed.setExpirationDate(pilotCertification.getExpirationDate());
		managed.setEffectiveDate(pilotCertification.getEffectiveDate());
		managed.setPassed(pilotCertification.getPassed());
		managed.setUser(pilotCertification.getUser());
		managed.setCertification(pilotCertification.getCertification());

		em.persist(managed);

		em.flush();

		return managed;
	}

}
