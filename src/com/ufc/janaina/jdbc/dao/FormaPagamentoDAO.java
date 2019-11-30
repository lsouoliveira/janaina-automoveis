package com.ufc.janaina.jdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.FormaPagamento;

public class FormaPagamentoDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from FormaPagamento where id = :id");
		query.setParameter("id", id);
		
		FormaPagamento v = (FormaPagamento) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}
	
	@Override
	public void update(Object t) {
		
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Query query = null;
		
		manager.getTransaction().begin();
		manager.clear();
		query = manager.createQuery("update Venda set formaPagamento = null where formaPagamento = :formaPagamento");
		query.setParameter("formaPagamento", ((FormaPagamento)t));
		query.executeUpdate();
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		manager.clear();
		query = manager.createQuery("delete FormaPagamento where id = :id");
		query.setParameter("id", ((FormaPagamento)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	public List<FormaPagamento> getAll() {
		List<FormaPagamento> result = null;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from FormaPagamento");
		result = query.getResultList();
		manager.getTransaction().commit();
		
		return result;
	}
	
	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((FormaPagamento) t);
		manager.getTransaction().commit();
	}

	public FormaPagamento getByTipo(String pagamentoTipo) {
		FormaPagamento result = null;
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from FormaPagamento where pagamentoTipo = :pagamentoTipo");
		query.setParameter("pagamentoTipo", pagamentoTipo);
		if(query.getResultList().size() > 0)
			result = (FormaPagamento)query.getSingleResult();
		manager.getTransaction().commit();
		
		return result;
	}
}
