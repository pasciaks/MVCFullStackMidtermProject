package com.skilldistillery.repeat.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="experience_type_requirement")
public class ExperienceTypeRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column (name="minutes_required")
	private int minutesRequired;
	
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
