package com.skilldistillery.repeat.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "enabled")
	private Boolean enabled;

	@OneToMany(mappedBy="organization")
	private List<Aircraft> aircrafts;
	
	public Organization() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Aircraft> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(List<Aircraft> aircrafts) {
		this.aircrafts = aircrafts;
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
		Organization other = (Organization) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", enabled=" + enabled + "]";
	}



}
