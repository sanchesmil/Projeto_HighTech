package com.htcursos.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	static{
		entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static EntityManager abreConexao(){
		return entityManager;
	}
}
