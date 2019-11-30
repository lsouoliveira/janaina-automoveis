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
public class Avaliacao {
	@Id
	@GeneratedValue
	private int id;

	private String nota;
	private String comentario;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "teste")
	private Teste teste;	
	

	public Avaliacao() {}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Teste getTeste() {
		return teste;
	}


	public void setTeste(Teste teste) {
		this.teste = teste;
	}


	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}