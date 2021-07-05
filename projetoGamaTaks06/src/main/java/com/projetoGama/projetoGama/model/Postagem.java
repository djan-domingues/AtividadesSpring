package com.projetoGama.projetoGama.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	private String linkDoc;
	
	@Size(min=10, max=100)
	private String corpoTexto;
	
	private String vagas;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;


	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLinkDoc() {
		return linkDoc;
	}

	public void setLinkDoc(String linkDoc) {
		this.linkDoc = linkDoc;
	}

	public String getCorpoTexto() {
		return corpoTexto;
	}

	public void setCorpoTexto(String corpoTexto) {
		this.corpoTexto = corpoTexto;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



}
