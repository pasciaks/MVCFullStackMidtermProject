package com.skilldistillery.repeat.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	
	private String password;
	
	@CreationTimestamp
	@Column (name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column (name = "updated_at")
	private LocalDateTime updatedAt;
	
}
