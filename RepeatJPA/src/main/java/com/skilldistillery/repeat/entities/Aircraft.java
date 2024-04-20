package com.skilldistillery.repeat.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tail_number")
	private String tailNumber;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="aircraft_type_id")
	private AircraftType aircraftType;
	
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	public Aircraft() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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
		Aircraft other = (Aircraft) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", tailNumber=" + tailNumber + ", active=" + active + ", enabled=" + enabled
				+ "]";
	}

}
