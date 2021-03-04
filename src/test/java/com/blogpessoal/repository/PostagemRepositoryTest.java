package com.blogpessoal.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes para o repositório de postagem")
class PostagemRepositoryTest {
	
	@Autowired PostagemRepository postagemRepository;
	
	@Test
	void testBasico() {
		boolean test = true;
		assertEquals(test, true);
	}
	
	@Test
	void cadastraUsuario_Sucesso() {
		//criando uma postagem 
		Postagem post= new Postagem();
		post.setTitulo("Uma postagem usada para fazer testes");
		post.setTexto("quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		//salvo a postagem
		Postagem testeSave= postagemRepository.save(post);
		//faço os testes
		Assertions.assertThat(testeSave).isNotNull();
		Assertions.assertThat(testeSave.getId()).isNotNull();
		Assertions.assertThat(testeSave.getTexto()).isEqualTo(post.getTexto());
		
	}
	
	@Test
	void cadastraUsuario_TituloNulo() {
		// criando uma postagem sem titulo
		Postagem post = new Postagem();
		post.setTexto("quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		//experando a exception 
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> postagemRepository.save(post)
		);
		assertTrue(exception.getMessage().contains("não deve ser nulo"));
	}
	@Test
	void cadastraUsuario_TextoNulo() {
		// criando uma postagem sem titulo
		Postagem post = new Postagem();
		post.setTitulo("Uma postagem usada para fazer testes");
		//experando a exception 
		Exception exception= assertThrows(
				ConstraintViolationException.class, 
				()-> postagemRepository.save(post)
		);
		assertTrue(exception.getMessage().contains("não deve ser nulo"));
	}
}
