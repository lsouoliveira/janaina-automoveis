package com.ufc.janaina.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ufc.janaina.models.Venda.Status;

@Entity
public class FormaPagamento {
	@Id
	@GeneratedValue
	private int id;
	private String pagamentoTipo;
	
	public FormaPagamento() {
		
	}
	
	public FormaPagamento(String selectedItem) {
		pagamentoTipo = selectedItem;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPagamentoTipo() {
		return pagamentoTipo;
	}
	public void setPagamentoTipo(String pagamentoTipo) {
		this.pagamentoTipo = pagamentoTipo;
	}
}
