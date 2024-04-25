package com.skilldistillery.repeat.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.repeat.entities.AircraftType;
import com.skilldistillery.repeat.entities.Certification;
import com.skilldistillery.repeat.entities.ExperienceType;
import com.skilldistillery.repeat.entities.PilotCertification;
import com.skilldistillery.repeat.entities.PilotLogEntry;

public interface CommanderDAO {

	List<AircraftType> findAllAircraftType();
	
}
