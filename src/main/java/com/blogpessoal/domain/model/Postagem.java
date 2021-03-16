package com.blogpessoal.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
public class Postagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Getter @Setter
	private long id;
	
	//@Getter @Setter 
	@NotNull (message = "tem que existir um título")
	@Size(min= 5, max= 100)
	private String titulo;
	
	//@Getter @Setter
	@NotNull @Size(min= 10, max= 500)
	private String texto; 
	
	//@Getter @Setter 
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date data= new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JsonIgnoreProperties("postagemList")
	private Tema tema;	
	
	@ManyToOne
	@JsonIgnoreProperties("postagensUsuario")
	UsuarioEntidade usuario;
	
	@Override
	public String toString() {
		return "Postagem [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", data=" + data + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public UsuarioEntidade getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntidade usuario) {
		this.usuario = usuario;
	}
	
	
	

}
