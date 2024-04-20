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
	
	@OneToMany(mappedBy="organization")
	private List<User> users;
	
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
	public void addAircraft(Aircraft aircraft) {
	    if (aircrafts == null) {
	       aircrafts = new ArrayList<>();
	    }
	    if (!aircrafts.contains(aircraft)) {
	        aircrafts.add(aircraft);
	        if (aircraft.getOrganization() != null && !aircraft.getOrganization().equals(this)) {
	            aircraft.getOrganization().removeAircraft(aircraft);
	        }
	        aircraft.setOrganization(this);
	    }
	}
	public void removeAircraft(Aircraft aircraft) {
	    if (aircrafts != null && aircrafts.contains(aircraft)) {
	        aircrafts.remove(aircraft);
	        aircraft.setOrganization(null);
	    }
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
	    if (users == null) {
	       users = new ArrayList<>();
	    }
	    if (!users.contains(user)) {
	        users.add(user);
	        if (user.getOrganization() != null && !user.getOrganization().equals(this)) {
	            user.getOrganization().removeUser(user);
	        }
	        user.setOrganization(this);
	    }
	}
	public void removeUser(User user) {
	    if (users != null && users.contains(user)) {
	        users.remove(user);
	        user.setOrganization(null);
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
		Organization other = (Organization) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", enabled=" + enabled + "]";
	}



}
