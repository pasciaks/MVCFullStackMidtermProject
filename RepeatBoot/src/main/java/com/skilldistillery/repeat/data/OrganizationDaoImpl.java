package com.skilldistillery.repeat.data;

import org.springframework.stereotype.Service;

import com.skilldistillery.repeat.entities.Organization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrganizationDaoImpl implements OrganizationDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Organization findById(int id) {

		Organization organization = new Organization();
		try {
			organization = em.find(Organization.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			organization = null;
		}
		return organization;
		
	}
	

}
