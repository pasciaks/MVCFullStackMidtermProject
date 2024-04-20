package com.skilldistillery.repeat.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="experience_type")
public class ExperienceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;
	
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="experience_type_requirement_id")
	private ExperienceTypeRequirement experienceTypeRequirement;
	

	public ExperienceType() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ExperienceTypeRequirement getExperienceTypeRequirement() {
		return experienceTypeRequirement;
	}

	public void setExperienceTypeRequirement(ExperienceTypeRequirement experienceTypeRequirement) {
		this.experienceTypeRequirement = experienceTypeRequirement;
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
		ExperienceType other = (ExperienceType) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ExperienceType [id=" + id + ", description=" + description + ", enabled=" + enabled + "]";
	}

	
	
}
