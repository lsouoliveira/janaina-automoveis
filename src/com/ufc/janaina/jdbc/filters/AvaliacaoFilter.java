package com.ufc.janaina.jdbc.filters;

import java.util.Date;

import com.ufc.janaina.dao.Filter;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.models.Venda;

public class AvaliacaoFilter implements Filter{
	public String modelo;
	public String chassi;
	public Date periodoInicio;
	public Date periodoFim;
}
