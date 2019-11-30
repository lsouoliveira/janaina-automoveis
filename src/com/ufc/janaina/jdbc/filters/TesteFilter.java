package com.ufc.janaina.jdbc.filters;

import java.util.Date;

import com.ufc.janaina.dao.Filter;

public class TesteFilter implements Filter{
	public String nome;
	public String rg;
	public String modelo;
	public String cor;
	public String placa;
	public String chassi;
	public Date dataRealizao;
	public Date dataCadastro;
	public Date horarioRealizao;
	public Date horarioCadastro;
	public boolean testesNaoRealizados;
	public boolean testesNaoAvaliados;
}
