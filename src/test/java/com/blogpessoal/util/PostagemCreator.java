package com.blogpessoal.util;

import com.blogpessoal.domain.model.Postagem;

public class PostagemCreator {
	public static Postagem criaPostagem() {
		Postagem post= new Postagem();
		post.setTitulo("Uma postagem usada para fazer testes");
		post.setTexto("quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		return post;
	}
	
	public Postagem criaPostagem_Save() {
		Postagem post= new Postagem();
		post.setId(1L);
		post.setTitulo("Uma postagem usada para fazer testes");
		post.setTexto("quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		return post;
	}
	
	public Postagem criaPostagem_Update() {
		Postagem post= new Postagem();
		post.setId(1L);
		post.setTitulo("{Update}Uma postagem usada para fazer testes");
		post.setTexto("[Update] quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		return post;
	}
}
