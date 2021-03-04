package com.blogpessoal.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Testes para o repositório de postagem")
class PostagemRepositoryTest {
	
	@Autowired PostagemRepository postagemRepository;
	
	public Postagem criaPostagem() {
		Postagem post= new Postagem();
		post.setTitulo("Uma postagem usada para fazer testes");
		post.setTexto("quero um caso de sucesso, então o texto tem mais de 10 caracteres");
		return post;
	}
	
	public void testePostagem(Postagem post1, Postagem post2) {
		Assertions.assertThat(post1).isNotNull();
		Assertions.assertThat(post1.getId()).isNotNull();
		Assertions.assertThat(post1.getTexto()).isEqualTo(post2.getTexto());
		Assertions.assertThat(post1.getTitulo()).isEqualTo(post2.getTitulo());

	}
	@Test
	void testBasico() {
		boolean test = true;
		assertEquals(test, true);
	}
	
	@Test
	void cadastraPostagem_Sucesso() {
		//criando uma postagem 
		Postagem post= this.criaPostagem();
		//salvo a postagem
		Postagem testeSave= postagemRepository.save(post);
		//faço os testes
		this.testePostagem(testeSave, post);
	}
	
	@Test
	void cadastraPostagem_TituloNulo() {
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
	void cadastraPostagem_TextoNulo() {
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
	
	@Test
	void alteraPostagem_Sucesso() {
		// criando uma postagem
		Postagem post = this.criaPostagem();
		// salvo a postagem
		Postagem postSaved = postagemRepository.save(post);
		//alteranto o poste
		postSaved.setTitulo("Titulo alterado");
		postSaved.setTexto("texto alterado");
		//salvo a alteração 
		Postagem postUpdate= postagemRepository.save(postSaved);
		//faço os testes 
		Assertions.assertThat(postUpdate.getId()).isEqualTo(postSaved.getId());
		this.testePostagem(postUpdate, postSaved);
	}
	
	@Test
	void deletePostagemPorId_Sucesso() {
		// criando uma postagem
		Postagem post = this.criaPostagem();
		// salvo a postagem
		Postagem postSaved = postagemRepository.save(post);
		//deleto por id 
		postagemRepository.deleteById(postSaved.getId());
		//verifico se deletou mesmo 
		Optional<Postagem> buscaPost= postagemRepository.findById(postSaved.getId());
		Assertions.assertThat(buscaPost.isEmpty()).isTrue();
	}
	
	@Test
	void deletePostagem_IdNaoExistente() {
		//deleto com um id que nao existe 
		Exception exception= assertThrows(
				EmptyResultDataAccessException.class, 
				()-> postagemRepository.deleteById(251L)
		);
		assertTrue(exception.getMessage().contains("id 251 exists!"));
	}
}
