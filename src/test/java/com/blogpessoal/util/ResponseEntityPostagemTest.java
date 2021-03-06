package com.blogpessoal.util;

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
	
	
}
