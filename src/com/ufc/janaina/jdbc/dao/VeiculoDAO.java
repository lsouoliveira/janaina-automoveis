package com.ufc.janaina.jdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.Veiculo;

public class VeiculoDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Veiculo where id = :id");
		query.setParameter("id", id);
		
		Veiculo v = (Veiculo) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Veiculo v = (Veiculo) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("update Veiculo set placa=:placa, ano=:ano, modelo=:modelo, chassi=:chassi, cor=:cor, preco = :preco where id = :id");
		query.setParameter("id", v.getId());
		query.setParameter("placa", v.getPlaca());
		query.setParameter("ano", v.getAno());
		query.setParameter("modelo", v.getModelo());
		query.setParameter("chassi", v.getChassi());
		query.setParameter("cor", v.getCor());
		query.setParameter("preco", v.getPreco());
		
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Veiculo where id = :id");
		query.setParameter("id", ((Veiculo)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Veiculo> getAll(Veiculo filter){
		List<Veiculo> lista;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "from Veiculo where placa LIKE :placa AND modelo LIKE :modelo AND chassi LIKE :chassi AND cor LIKE :cor";
		
		if(filter.getAno() != -1) 
			sql +=  " AND ano = :ano";
		if(filter.getPreco() != -1.0) //pegar preco como preco maximo
			sql += " AND preco <= :preco";
		
		System.out.println("CHABARAGUATANGA!!!!!!!!!!!1 " + filter.getPreco());
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		query.setParameter("placa", "%" + filter.getPlaca() + "%");
		query.setParameter("modelo", "%" + filter.getModelo() + "%");
		query.setParameter("chassi", "%" + filter.getChassi() + "%");
		query.setParameter("cor", "%" + filter.getCor() + "%");
		
		if(filter.getAno() != -1) 
			query.setParameter("ano", filter.getAno());
		if(filter.getPreco() != -1)
			query.setParameter("preco", filter.getPreco());
		
		lista = query.getResultList();
		manager.getTransaction().commit();
		
		return lista;
	}
	
	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Veiculo) t);
		manager.getTransaction().commit();
	}
	
}










