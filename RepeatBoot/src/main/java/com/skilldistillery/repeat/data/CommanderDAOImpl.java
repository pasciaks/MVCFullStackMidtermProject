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
public class CommanderDAOImpl implements CommanderDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AircraftType> findAllAircraftType() {
		String jpql = "SELECT aircraft from AircraftType aircraft order by aircraft.id asc";
		List<AircraftType> aircraftTypes = new ArrayList<>();
		aircraftTypes = em.createQuery(jpql, AircraftType.class).getResultList();
		System.out.println(aircraftTypes.size());
		System.out.println(aircraftTypes);
		return aircraftTypes;
	}

}
