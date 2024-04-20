package com.skilldistillery.repeat.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="experience_type_requirement")
public class ExperienceTypeRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="minutes_required")
	private int minutesRequired;
	
	@ManyToOne
	@JoinColumn(name="aircraft_type_id")
	private AircraftType aircraftType;
	
	@OneToMany(mappedBy="experienceTypeRequirement")
	private List<ExperienceType> experienceTypes;
	
	public ExperienceTypeRequirement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getMinutesRequired() {
		return minutesRequired;
	}

	public void setMinutesRequired(int minutesRequired) {
		this.minutesRequired = minutesRequired;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public List<ExperienceType> getExperienceTypes() {
		return experienceTypes;
	}

	public void setExperienceTypes(List<ExperienceType> experienceTypes) {
		this.experienceTypes = experienceTypes;
	}
	
	public void addExperienceType(ExperienceType experiencetype) {
	    if (experienceTypes == null) {
	       experienceTypes = new ArrayList<>();
	    }
	    if (!experienceTypes.contains(experiencetype)) {
	        experienceTypes.add(experiencetype);
	        if (experiencetype.getExperienceTypeRequirement() != null && !experiencetype.getExperienceTypeRequirement().equals(this)) {
	            experiencetype.getExperienceTypeRequirement().removeExperienceType(experiencetype);
	        }
	        experiencetype.setExperienceTypeRequirement(this);
	    }
	}
	public void removeExperienceType(ExperienceType experiencetype) {
	    if (experienceTypes != null && experienceTypes.contains(experiencetype)) {
	        experienceTypes.remove(experiencetype);
	        experiencetype.setExperienceTypeRequirement(null);
	    }
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExperienceTypeRequirement other = (ExperienceTypeRequirement) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ExperienceTypeRequirement [id=" + id + ", minutesRequired=" + minutesRequired + "]";
	}

	
}
