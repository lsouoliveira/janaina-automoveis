package com.ufc.janaina.jdbc.filters;

import java.util.Date;

import com.ufc.janaina.dao.Filter;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.models.Venda;

public class VendaFilter implements Filter{
	public String rg;
	public String nome;
	public String placa;
	public String chassi;
	public Date periodoInicio;
	public Date periodoFim;
	public Venda.Status status;
	public FormaPagamento formaPagamento;
}
