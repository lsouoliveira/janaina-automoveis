package com.ufc.janaina.jdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.Montadora;

public class MontadoraDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Montadora where id = :id");
		query.setParameter("id", id);
		
		Montadora v = (Montadora) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Montadora v = (Montadora) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("update Montadora set cnpj = :cnpj, nome = :nome, telefone = :telefone, endereco = :endereco, bairro = :bairro, cidade = :cidade, uf = :uf, numero = :numero where id = :id");
		query.setParameter("id", v.getId());
		query.setParameter("cnpj", v.getCnpj());
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
		Query query = manager.createQuery("delete Montadora where id = :id");
		query.setParameter("id", ((Montadora)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Montadora> getAll(Montadora v){
		List<Montadora> lista;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "from Montadora where cnpj LIKE :cnpj AND nome LIKE :nome AND telefone LIKE :telefone AND endereco LIKE :endereco AND bairro LIKE :bairro AND cidade LIKE :cidade AND uf LIKE :uf";
		
		if(v.getUf().contentEquals("UF")) {
			v.setUf("");
		}
		
		if(v.getNumero() != -1) {
			sql += " AND numero = :numero";
		}

		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		query.setParameter("cnpj", "%" + v.getCnpj() + "%");
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
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Montadora) t);
		manager.getTransaction().commit();
	}
	
}
