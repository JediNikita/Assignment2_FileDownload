package com.example.demo.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;

import com.example.demo.Model.Message;

@Component
public class FileService {
	
	public void save( Message message) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(message);
		
		em.getTransaction().commit();

		// close the entity manager
		em.close();
		entityManagerFactory.close();

	}
}
