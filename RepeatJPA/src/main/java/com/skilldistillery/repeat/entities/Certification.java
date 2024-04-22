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
@Table(name = "certification")
public class Certification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "description")
    private String description;
    
    @Column(name = "required")
    private Boolean required;
    
	@OneToMany(mappedBy="certification")
	private List<PilotCertification> pilotCertifications;
	
	public Certification() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public List<PilotCertification> getPilotCertifications() {
		return pilotCertifications;
	}

	public void setPilotCertifications(List<PilotCertification> pilotCertifications) {
		this.pilotCertifications = pilotCertifications;
	}

	public void addPilotCertification(PilotCertification pilotcertification) {
	    if (pilotCertifications == null) {
	       pilotCertifications = new ArrayList<>();
	    }
	    if (!pilotCertifications.contains(pilotcertification)) {
	        pilotCertifications.add(pilotcertification);
	        if (pilotcertification.getCertification() != null && !pilotcertification.getCertification().equals(this)) {
	            pilotcertification.getCertification().removePilotCertification(pilotcertification);
	        }
	        pilotcertification.setCertification(this);
	    }
	}
	public void removePilotCertification(PilotCertification pilotcertification) {
	    if (pilotCertifications != null && pilotCertifications.contains(pilotcertification)) {
	        pilotCertifications.remove(pilotcertification);
	        pilotcertification.setCertification(null);
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
		Certification other = (Certification) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Certification [id=" + id + ", description=" + description + ", required=" + required + "]";
	}


}
