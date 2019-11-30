
package com.ufc.janaina.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Teste {
	@Id
	@GeneratedValue
	private int id;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_cadastro;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_realizacao;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario_cadastro;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario_realizacao;
	
	private boolean realizado;
	private boolean avaliado;
	
	@ManyToOne
	@JoinColumn(name = "veiculo")
	private Veiculo veiculo;	
	
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	public Teste() {}
	
	public Teste(Date data_cadastro, Date data_realizacao, Veiculo veiculo, Cliente cliente ) {
		
		this.data_cadastro = data_cadastro;
		this.data_realizacao = data_realizacao;
		this.veiculo = veiculo;
		this.cliente = cliente;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean isRealizado() {
		return realizado;
	}

	public Date getHorario_cadastro() {
		return horario_cadastro;
	}

	public void setHorario_cadastro(Date horario_cadastro) {
		this.horario_cadastro = horario_cadastro;
	}

	public Date getHorario_realizacao() {
		return horario_realizacao;
	}

	public void setHorario_realizacao(Date horario_realizacao) {
		this.horario_realizacao = horario_realizacao;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}
	public boolean isAvaliado() {
		return avaliado;
	}
	public void setAvaliado(boolean avaliado) {
		this.avaliado = avaliado;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public Date getData_realizacao() {
		return data_realizacao;
	}

	public void setData_realizacao(Date data_realizacao) {
		this.data_realizacao = data_realizacao;
	}
}
