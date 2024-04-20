package com.skilldistillery.repeat.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private Boolean enabled;
	
	@CreationTimestamp
	@Column (name = "created_at")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column (name = "updated_at")	
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@OneToMany(mappedBy="user")
	private List<PilotLogEntry> pilotLogEntries;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public List<PilotLogEntry> getPilotLogEntries() {
		return pilotLogEntries;
	}

	public void setPilotLogEntries(List<PilotLogEntry> pilotLogEntries) {
		this.pilotLogEntries = pilotLogEntries;
	}
	
	public void addPilotLogEntry(PilotLogEntry pilotlogentry) {
	    if (pilotLogEntries == null) {
	       pilotLogEntries = new ArrayList<>();
	    }
	    if (!pilotLogEntries.contains(pilotlogentry)) {
	        pilotLogEntries.add(pilotlogentry);
	        if (pilotlogentry.getUser() != null && !pilotlogentry.getUser().equals(this)) {
	            pilotlogentry.getUser().removePilotLogEntry(pilotlogentry);
	        }
	        pilotlogentry.setUser(this);
	    }
	}
	public void removePilotLogEntry(PilotLogEntry pilotlogentry) {
	    if (pilotLogEntries != null && pilotLogEntries.contains(pilotlogentry)) {
	        pilotLogEntries.remove(pilotlogentry);
	        pilotlogentry.setUser(null);
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
		User other = (User) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", dateOfBirth=" + dateOfBirth
				+ ", imageUrl=" + imageUrl + ", enabled=" + enabled + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	
}
