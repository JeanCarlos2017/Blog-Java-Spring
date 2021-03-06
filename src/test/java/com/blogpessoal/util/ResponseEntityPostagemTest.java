package com.blogpessoal.util;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.domain.model.Postagem;


public class ResponseEntityPostagemTest {
	public static void testePostagemUnica(ResponseEntity<Postagem> post, HttpStatus status) {
		// teste no response entity
		Assertions.assertThat(post.getStatusCode()).isEqualTo(status);
		Assertions.assertThat(post.getBody()).isNotNull();
		Assertions.assertThat(post.getBody().getTexto()).isEqualTo(post.getBody().getTexto());
	}
	
	public static void testePostagemUnicaVazia(ResponseEntity<Postagem> post, HttpStatus status) {
		Assertions.assertThat(post.getStatusCode()).isEqualTo(status);
		Assertions.assertThat(post.getBody()).isNull();
	}
	
	public static void testeListaPostagem(ResponseEntity<List<Postagem>> responsePostList, HttpStatus status) {
		Assertions.assertThat(responsePostList.getStatusCode()).isEqualTo(status);
		Assertions.assertThat(responsePostList.getBody())
													.isNotNull()
													.isNotEmpty()
													.hasSize(1);
		Assertions.assertThat(responsePostList.getBody().get(0).getTitulo())
											.isEqualTo(PostagemCreator.criaPostagem_Save().getTitulo());	
	}
}
