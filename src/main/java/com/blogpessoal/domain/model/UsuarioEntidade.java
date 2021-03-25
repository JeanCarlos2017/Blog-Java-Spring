package com.blogpessoal.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity @Table(name="tb_usuario")
public class UsuarioEntidade {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_usuario;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String nome;
	
	private String url_foto;
	
	private long codigo_usuario;
	
	private String tipo;
	
	@OneToMany(mappedBy= "usuario", cascade= CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagensUsuario= new ArrayList<Postagem>();

	public UsuarioEntidade() {
		
	}
	
	public long gerarCodigo() {
		long codigoUsuario= 0L;
		int lenght= this.nome.length();
		for(int i= 0; i < lenght; i++) {
			codigoUsuario += nome.codePointAt(i);
		}
		return codigoUsuario;
	}
	
	//getters and setters 
	public long getId_usuario() {
		return id_usuario;
	}


	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCodigo_usuario() {
		return codigo_usuario;
	}


	public void setCodigo_usuario() {
		this.codigo_usuario = this.gerarCodigo();
	}

	public String getUrl_foto() {
		return url_foto;
	}

	public void setUrl_foto(String url_foto) {
		this.url_foto = url_foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Postagem> getPostagensUsuario() {
		return postagensUsuario;
	}

	public void setPostagensUsuario(List<Postagem> postagensUsuario) {
		this.postagensUsuario = postagensUsuario;
	}
	
	
	
}