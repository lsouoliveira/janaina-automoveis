package com.ufc.janaina.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {
	private static DatabaseConnection instance = null;
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public static DatabaseConnection getInstance() {
		if(instance == null) {
			instance = new DatabaseConnection();
		}
		
		return instance;
	}
	
	public EntityManager getEntityManager() {
		return manager;
	}
	
	public void init() {
		factory = Persistence.createEntityManagerFactory("default");
		manager = factory.createEntityManager();
	}
	
	public void close() {
		manager.close();
		factory.close();
	}
}
