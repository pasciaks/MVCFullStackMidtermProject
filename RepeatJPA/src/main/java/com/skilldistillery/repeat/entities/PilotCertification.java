package com.skilldistillery.repeat.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pilot_certification")
public class PilotCertification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "effective_date")
	private LocalDate effectiveDate;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "passed")
	private Boolean passed;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	
	public PilotCertification() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getPassed() {
		return passed;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
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
		PilotCertification other = (PilotCertification) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "PilotCertification [id=" + id + ", effectiveDate=" + effectiveDate + ", details=" + details
				+ ", passed=" + passed + ", expirationDate=" + expirationDate + "]";
	}


}
