package com.ufc.janaina.jdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.Cliente;

public class ClienteDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Cliente where id = :id");
		query.setParameter("id", id);
		
		Cliente v = (Cliente) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Cliente v = (Cliente) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		
		Query query = manager.createQuery("update Cliente set rg = :rg, nome = :nome, telefone = :telefone, endereco = :endereco, bairro = :bairro, cidade = :cidade, uf = :uf, numero = :numero where id = :id");
		
		query.setParameter("id", v.getId());
		query.setParameter("rg", v.getRg());
		query.setParameter("nome", v.getNome());
		query.setParameter("telefone", v.getTelefone());
		query.setParameter("endereco", v.getEndereco());
		query.setParameter("bairro", v.getBairro());
		query.setParameter("cidade", v.getCidade());
		query.setParameter("uf", v.getUf());
		query.setParameter("numero", v.getNumero());
		
		query.executeUpdate();
		
		manager.getTransaction().commit();
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Cliente where id = :id");
		query.setParameter("id", ((Cliente)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getAll(Cliente v){
		List<Cliente> lista;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "from Cliente where rg LIKE :rg AND nome LIKE :nome AND telefone LIKE :telefone AND endereco LIKE :endereco AND bairro LIKE :bairro AND cidade LIKE :cidade AND uf LIKE :uf";
		
		if(v.getUf().contentEquals("UF")) {
			v.setUf("");
		}
		
		if(v.getNumero() != -1) {
			sql += " AND numero = :numero";
		}
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		query.setParameter("rg", "%" + v.getRg() + "%");
		query.setParameter("nome", "%" + v.getNome() + "%");
		query.setParameter("telefone", "%" + v.getTelefone() + "%");
		query.setParameter("endereco", "%" + v.getEndereco() + "%");
		query.setParameter("bairro", "%" + v.getBairro() + "%");
		query.setParameter("cidade", "%" + v.getCidade() + "%");
		query.setParameter("uf", "%" + v.getUf() + "%");
		if(v.getNumero() != -1) {
			query.setParameter("numero", v.getNumero());
		}
		lista = query.getResultList();
		manager.getTransaction().commit();
		
		return lista;
	}
	
	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		System.out.println("testando " + ((Cliente)t).getRg());
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Cliente) t);
		manager.getTransaction().commit();
	}
	
}
