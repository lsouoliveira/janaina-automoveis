package com.ufc.janaina.jdbc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.models.Usuario;
import com.ufc.janaina.models.Vendedor;

public class VendedorDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Vendedor v = null;
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Vendedor where id = :id");
		query.setParameter("id", id);
		
		if(query.getResultList().size() > 0) {
			v = (Vendedor) query.getSingleResult();
		}
		
		manager.getTransaction().commit();
		
		return v;
	}
	
	public Object getByUserId(int id) {
		Usuario u = (Usuario) new UserDAO().get(id);
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Vendedor v = null;
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Vendedor where usuario = :usuario");
		query.setParameter("usuario", u);
		
		if(query.getResultList().size() > 0) {
			v = (Vendedor) query.getSingleResult();
		}
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Vendedor v = (Vendedor) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("update Vendedor set rg = :rg, nome = :nome, telefone = :telefone, endereco = :endereco, bairro = :bairro, cidade = :cidade, uf = :uf, numero = :numero where id = :id");
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
		
		new UserDAO().update(v.getUsuario());
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Vendedor v = (Vendedor) t;
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Vendedor where id = :id");
		query.setParameter("id", ((Vendedor)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
		
		new UserDAO().delete(v.getUsuario());
	}

	@SuppressWarnings("unchecked")
	public List<Vendedor> getAll(Vendedor v){
		List<Vendedor> lista = null;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "from Vendedor where (rg is null OR rg LIKE :rg) AND (nome is null OR nome LIKE :nome) AND (telefone is null OR telefone LIKE :telefone) AND (endereco is null OR endereco LIKE :endereco) AND (bairro is null OR bairro LIKE :bairro) AND (cidade is null OR cidade LIKE :cidade) AND (uf is null OR uf LIKE :uf)";
		
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
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Vendedor) t);
		manager.getTransaction().commit();
	}
	
}
