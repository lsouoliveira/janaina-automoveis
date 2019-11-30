package com.ufc.janaina.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Venda {
	public enum Status {
		AGUARDANDO_PAGAMENTO(0), PAGO(1), CANCELADO(2);
		
		private int value;
		
		private Status(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	};
	
	@Id
	@GeneratedValue
	private int id;
	private String descricao;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToOne
	@JoinColumn(name = "formapagamento_id")
	private FormaPagamento formaPagamento;
	
	@OneToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public static Status toStatus(String status) {
		if(status.contentEquals("AGUARDANDO PAGAMENTO")) {
			return Status.AGUARDANDO_PAGAMENTO;
		}else if(status.contentEquals("PAGO")) {
			return Status.PAGO;
		}else if(status.contentEquals("CANCELADO")) {
			return Status.CANCELADO;
		}
		
		return null;
	}
	public static String getStatusAsString(Status status) {
		switch(status) {
			case AGUARDANDO_PAGAMENTO:
				return "AGUARDANDO PAGAMENTO";
			case PAGO:
				return "PAGO";
			case CANCELADO:
				return "CANCELADO";
		}
		return "UNDEFINED";
	}
}
