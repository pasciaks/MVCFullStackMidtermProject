package com.skilldistillery.repeat.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.repeat.entities.AircraftType;
import com.skilldistillery.repeat.entities.Certification;
import com.skilldistillery.repeat.entities.ExperienceType;
import com.skilldistillery.repeat.entities.PilotCertification;
import com.skilldistillery.repeat.entities.PilotLogEntry;

public interface PilotDAO {

	PilotLogEntry addPilotLog(LocalDateTime startTime, LocalDateTime stopTime, int experienceTypeId, int userdId);

	List<ExperienceType> findAllExperienceTypes();
	
	List<ExperienceType> findAllExperienceTypesByAircraftTypeId(int aircraftTypeId);

	List<PilotLogEntry> findAllPilotLogEntries(int userId);
	
	List<PilotLogEntry> findAllPilotLogEntries(int userId, LocalDateTime startedAfterDateTime);

	PilotLogEntry findPilotLogEntryById(int id);

	PilotLogEntry updatePilotLog(int id, PilotLogEntry pilotLogEntry);

	ExperienceType findById(int id);
	
	List<AircraftType> findAllAircraftType();
	
	Boolean deletePilotLogEntryById(int id);
	
	Boolean deletePilotCertificationById(int id);

	List<PilotCertification> findAllPilotCertification(int pilotId);
	
	List<Certification> findAllCertification();
	
	PilotCertification addPilotCertification(PilotCertification pilotCertification);
	
	Certification findCertificationById(int id);
	
}
