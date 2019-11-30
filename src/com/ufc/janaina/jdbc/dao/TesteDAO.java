package com.ufc.janaina.jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.jdbc.filters.TesteFilter;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.models.Teste;
import com.ufc.janaina.models.Veiculo;

public class TesteDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Teste where id = :id");
		query.setParameter("id", id);
		
		Teste t = (Teste) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return t;
	}

	@Override
	public void update(Object o) {
		Teste t = (Teste) o;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		
		Query query = manager.createQuery("update Teste set realizado = :realizado, avaliado = :avaliado, "
				+ "data_cadastro = :data_cadastro, data_realizacao = :data_realizacao, "
				+ "horario_cadastro = :horario_cadastro, horario_realizacao = :horario_realizacao, "
				+ "cliente = :cliente, veiculo = :veiculo where id = :id");
		
		query.setParameter("id", t.getId());
		query.setParameter("data_cadastro", t.getData_cadastro());
		query.setParameter("data_realizacao", t.getData_realizacao());
		query.setParameter("horario_cadastro", t.getHorario_cadastro());
		query.setParameter("horario_realizacao", t.getHorario_realizacao());
		query.setParameter("cliente", t.getCliente());
		query.setParameter("veiculo", t.getVeiculo());
		query.setParameter("realizado", t.isRealizado());
		query.setParameter("avaliado", t.isAvaliado());
		query.executeUpdate();
		
		manager.getTransaction().commit();
	}
	
	public void setRealizado(int id, boolean r) {
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		
		Query query = manager.createQuery("update Teste set realizado = :realizado where id = :id");
		
		query.setParameter("id", id);
		query.setParameter("realizado", r);
		query.executeUpdate();
		
		manager.getTransaction().commit();
	}

	@Override
	public void delete(Object o) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		Query query = null;
		
		manager.getTransaction().begin();
		manager.clear();
		query = manager.createQuery("update Avaliacao set teste = null where teste = :teste");
		query.setParameter("teste", ((Teste)o));
		query.executeUpdate();
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		manager.clear();
		query = manager.createQuery("delete Teste where id = :id");
		query.setParameter("id", ((Teste)o).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Teste> getAllDataCadastro(Teste v){
		List<Teste> lista;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "from Teste where data_cadastro LIKE :data_cadastro";
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		query.setParameter("data_cadastro", "%" + v.getData_cadastro() + "%");
		lista = query.getResultList();
		manager.getTransaction().commit();
		
		return lista;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Teste> getAll(TesteFilter v){
		List<Object[]> resultado;
		List<Teste> lista = new ArrayList<>();
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "select t.id, t.realizado, t.avaliado , t.data_cadastro, t.data_realizacao, t.horario_cadastro, t.horario_realizacao, veh, cli "
				+ "from Teste t left join Veiculo veh on t.veiculo = veh left join Cliente cli on t.cliente = cli "
				+ "where "
				+ "(veh.modelo is null OR veh.modelo LIKE :modelo) "
				+ "AND (veh.placa is null OR veh.placa LIKE :placa) "
				+ "AND (veh.chassi is null OR veh.chassi LIKE :chassi) "
				+ "AND (veh.cor is null OR veh.cor LIKE :cor) "
				+ "AND (cli.rg is null OR cli.rg LIKE :rg) "
				+ "AND (cli.nome is null OR cli.nome LIKE :nome)";
		
		if(v.testesNaoRealizados) {
			sql += " AND t.realizado = false";
		}
		
		if(v.testesNaoAvaliados) {
			sql += " AND t.avaliado = false";
		}
		
		if(v.dataCadastro != null) {
			sql += " AND t.data_cadastro = :data_cadastro";
		}
		
		if(v.dataRealizao != null) {
			sql += " AND t.data_realizacao = :data_realizacao";
		}
		
		if(v.horarioCadastro != null) {
			sql += " AND t.horario_cadastro = :horario_cadastro";
		}
		
		if(v.horarioRealizao != null) {
			sql += " AND t.horario_realizacao = :horario_realizacao";
		}
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		
		System.out.println(sql);
		
		if(v.dataCadastro != null)
		query.setParameter("data_cadastro", v.dataCadastro);
		
		if(v.dataRealizao != null)
		query.setParameter("data_realizacao", v.dataRealizao);
		
		if(v.horarioCadastro != null)
		query.setParameter("horario_cadastro", v.horarioCadastro);
		
		if(v.horarioRealizao != null)
		query.setParameter("horario_realizacao", v.horarioRealizao);
		
		query.setParameter("modelo", "%" + v.modelo + "%");
		query.setParameter("placa", "%" + v.placa + "%");
		query.setParameter("chassi", "%" + v.chassi + "%");
		query.setParameter("cor", "%" + v.cor + "%");
		query.setParameter("rg", "%" + v.rg + "%");
		query.setParameter("nome", "%" + v.nome + "%");
		resultado = query.getResultList();
		
		for(Object[] row : resultado) {
			Teste teste = new Teste();
			teste.setId((int)row[0]);
			teste.setRealizado((boolean)row[1]);
			teste.setAvaliado((boolean)row[2]);
			teste.setData_cadastro((Date)row[3]);
			teste.setData_realizacao((Date)row[4]);
			teste.setHorario_cadastro((Date)row[5]);
			teste.setHorario_realizacao((Date)row[6]);
			teste.setVeiculo((Veiculo)row[7]);
			teste.setCliente((Cliente)row[8]);
			lista.add(teste);
		}
		
		manager.getTransaction().commit();
		
		return lista;
	}
	
	@Override
	public void insert(Object o) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		System.out.println("testando " + ((Teste)o).getData_cadastro());
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Teste) o);
		manager.getTransaction().commit();
	}
	
}
