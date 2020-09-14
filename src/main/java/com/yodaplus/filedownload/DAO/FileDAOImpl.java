package com.yodaplus.filedownload.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.yodaplus.filedownload.model.Message;

@Repository
public class FileDAOImpl implements FileDAO{

	@Override
	public void save( Message message) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(message);
		em.getTransaction().commit();
		em.close();
		entityManagerFactory.close();

	}
}
