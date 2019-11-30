package com.ufc.janaina.jdbc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.Usuario;

public class UserDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Usuario where id = :id");
		query.setParameter("id", id);
		
		Usuario v = (Usuario) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}
	
	public Usuario getWithCredentials(String username, String password) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Usuario v = null;
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Usuario where username = :username and password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		if(query.getResultList().size() > 0) {
			v = (Usuario) query.getSingleResult();
		}
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Usuario v = (Usuario) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("update Usuario set username = :username, password = :password, adminlevel = :adminlevel where id = :id");
		query.setParameter("id", v.getId());
		query.setParameter("username", v.getUsername());
		query.setParameter("password", v.getPassword());
		query.setParameter("adminLevel", v.getAdminLevel());
		
		query.executeUpdate();
		
		manager.getTransaction().commit();
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Usuario where id = :id");
		query.setParameter("id", ((Usuario)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Usuario) t);
		manager.getTransaction().commit();
	}
	
}
