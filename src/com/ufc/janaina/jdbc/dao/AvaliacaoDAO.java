package com.ufc.janaina.jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.jdbc.filters.AvaliacaoFilter;
import com.ufc.janaina.models.Avaliacao;
import com.ufc.janaina.models.Teste;

public class AvaliacaoDAO implements Dao {
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();

		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Avaliacao where id = :id");
		query.setParameter("id", id);

		Avaliacao v = (Avaliacao) query.getSingleResult();

		manager.getTransaction().commit();

		return v;
	}

	@Override
	public void update(Object t) {

	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();

		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Avaliacao where id = :id");
		query.setParameter("id", ((Avaliacao) t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	public List<Avaliacao> getAll(AvaliacaoFilter av) {
		List<Avaliacao> result = new ArrayList<Avaliacao>();
		List<Object[]> lista = new ArrayList<Object[]>();

		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();

		manager.getTransaction().begin();
		manager.clear();
		String sql = "select v.id, v.comentario, v.nota, t, v.data from Avaliacao v left join Teste t on v.teste = t left join Veiculo veh on t.veiculo = veh " 
		+ "where ";
		sql += "(veh.modelo is null OR veh.modelo LIKE :modelo) ";
		sql += "AND (veh.chassi is null OR veh.chassi LIKE :chassi) ";
				
		if(av.periodoInicio != null && av.periodoFim != null) {
			sql += "AND v.data >= :inicio AND v.data <= :fim ";
		}
		
		Query query = manager.createQuery(sql);
		query.setParameter("modelo", "%" + av.modelo + "%");
		query.setParameter("chassi", "%" + av.chassi + '%');
		
		if(av.periodoInicio != null && av.periodoFim != null) {
			query.setParameter("inicio", av.periodoInicio);
			query.setParameter("fim", av.periodoFim);
		}
		
		lista = query.getResultList();
		
		for(Object[] row : lista) {
			Avaliacao avaliacao = new Avaliacao();
			
			avaliacao.setId((int)row[0]);
			avaliacao.setComentario((String)row[1]);
			avaliacao.setNota((String)row[2]);
			avaliacao.setTeste((Teste)row[3]);
			avaliacao.setData((Date)row[4]);
			
			result.add(avaliacao);
		}
		manager.getTransaction().commit();

		return result;
	}

	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();

		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Avaliacao) t);
		manager.getTransaction().commit();
	}

}
