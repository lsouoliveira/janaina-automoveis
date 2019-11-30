package com.ufc.janaina.jdbc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ufc.janaina.dao.Dao;
import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.jdbc.filters.VendaFilter;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.models.Venda;

public class VendaDAO implements Dao{
	@Override
	public Object get(int id) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("from Venda where id = :id");
		query.setParameter("id", id);
		
		Venda v = (Venda) query.getSingleResult();
		
		manager.getTransaction().commit();
		
		return v;
	}

	@Override
	public void update(Object t) {
		Venda v = (Venda) t;
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		
		Query query = manager.createQuery("update Venda set "
				+ "cliente = :cliente, veiculo = :veiculo, "
				+ "data = :data, status = :status, "
				+ "formaPagamento = :formaPagamento, "
				+ "descricao = :descricao where id = :id");
		
		query.setParameter("id", v.getId());
		query.setParameter("cliente", v.getCliente());
		query.setParameter("veiculo", v.getVeiculo());
		query.setParameter("data", v.getData());
		query.setParameter("status", v.getStatus());
		query.setParameter("formaPagamento", v.getFormaPagamento());
		query.setParameter("descricao", v.getDescricao());
		
		query.executeUpdate();
		
		manager.getTransaction().commit();
	}

	@Override
	public void delete(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery("delete Venda where id = :id");
		query.setParameter("id", ((Venda)t).getId());
		query.executeUpdate();
		manager.getTransaction().commit();
	}
	
	@Override
	public void insert(Object t) {
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		manager.getTransaction().begin();
		manager.clear();
		manager.persist((Venda) t);
		manager.getTransaction().commit();
	}
	
	public List<Venda> getAll(VendaFilter v){
		List<Object[]> lista;
		List<Venda> result = new ArrayList<Venda>();
		
		EntityManager manager = DatabaseConnection.getInstance().getEntityManager();
		
		String sql = "select v.id, v.data, v.descricao, v.status, cli, veh, f from Venda v left join Veiculo veh on v.veiculo = veh left join Cliente cli on v.cliente = cli left join FormaPagamento f on v.formaPagamento = f "
		+ "where "
		+ "(veh.placa is null OR veh.placa LIKE :placa) "
		+ "AND (veh.chassi is null OR veh.chassi LIKE :chassi) "
		+ "AND (cli.rg is null OR cli.rg LIKE :rg) "
		+ "AND (cli.nome is null OR cli.nome LIKE :nome) ";
		
		if(v.formaPagamento != null)
			sql += "AND (f.pagamentoTipo is null OR f.pagamentoTipo LIKE :pagamentoTipo) ";
		
		if(v.periodoInicio != null && v.periodoFim != null) {
			sql += "AND data >= :inicio AND data <= :fim ";
		}
		
		if(v.status != null) {
			sql += "AND status = :status ";
		}
		
		System.out.println(sql);
		
		manager.getTransaction().begin();
		manager.clear();
		Query query = manager.createQuery(sql);
		query.setParameter("placa", "%" + v.placa + "%");
		query.setParameter("chassi", "%" + v.chassi + "%");
		query.setParameter("rg", "%" + v.rg + "%");
		query.setParameter("nome", "%" + v.nome + "%");
		
		if(v.periodoInicio != null && v.periodoFim != null) {
			query.setParameter("inicio", v.periodoInicio);
			query.setParameter("fim", v.periodoFim);
		}
		
		if(v.status != null) {
			query.setParameter("status", v.status.getValue());
		}
		
		if(v.formaPagamento != null)
			query.setParameter("pagamentoTipo", "%" + v.formaPagamento.getPagamentoTipo() + "%");
		
		lista = query.getResultList();
		
		for(Object[] row : lista) {
			Venda venda = new Venda();
			
			venda.setId((int)row[0]);
			venda.setData((Date)row[1]);
			venda.setDescricao((String)row[2]);
			venda.setStatus((Venda.Status)row[3]);
			venda.setCliente((Cliente)row[4]);
			venda.setVeiculo((Veiculo)row[5]);
			venda.setFormaPagamento((FormaPagamento)row[6]);
			
			result.add(venda);
		}
		
		manager.getTransaction().commit();
		
		return result;
	}
}
