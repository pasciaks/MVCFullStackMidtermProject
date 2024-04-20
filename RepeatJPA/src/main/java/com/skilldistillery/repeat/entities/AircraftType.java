package com.skilldistillery.repeat.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="aircraft_type")
public class AircraftType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "aircraft_type")
	private String aircraftType;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@OneToMany(mappedBy="aircraftType")
	private List<Aircraft> aircrafts;
	
	
	public AircraftType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean eanbled) {
		this.enabled = eanbled;
	}

	
	public List<Aircraft> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(List<Aircraft> aircrafts) {
		this.aircrafts = aircrafts;
	}
	public void addAircraft(Aircraft aircraft) {
	    if (aircrafts == null) {
	       aircrafts = new ArrayList<>();
	    }
	    if (!aircrafts.contains(aircraft)) {
	        aircrafts.add(aircraft);
	        if (aircraft.getAircraftType() != null && !aircraft.getAircraftType().equals(this)) {
	            aircraft.getAircraftType().removeAircraft(aircraft);
	        }
	        aircraft.setAircraftType(this);
	    }
	}
	public void removeAircraft(Aircraft aircraft) {
	    if (aircrafts != null && aircrafts.contains(aircraft)) {
	        aircrafts.remove(aircraft);
	        aircraft.setAircraftType(null);
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
		AircraftType other = (AircraftType) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "AircraftType [id=" + id + ", aircraftType=" + aircraftType + ", imageUrl=" + imageUrl + ", eanbled="
				+ enabled + "]";
	}


}
