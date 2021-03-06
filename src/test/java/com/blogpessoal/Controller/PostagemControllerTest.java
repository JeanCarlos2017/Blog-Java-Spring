package com.blogpessoal.Controller;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.blogpessoal.api.controller.PostagemController;
import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;
import com.blogpessoal.domain.service.CadastroPostagemService;
import com.blogpessoal.util.PostagemCreator;
import com.blogpessoal.util.ResponseEntityPostagemTest;

@ExtendWith(SpringExtension.class)
@DisplayName("[Teste]Postagem Controller")
public class PostagemControllerTest {
	@InjectMocks //quero testar a classe em si
	private PostagemController postagemController;
	@Mock 
	private CadastroPostagemService postService;
	@Mock 
	private PostagemRepository postRepository;
	
	@BeforeEach
	void setUp() {
		//quando chamar o postService.getPostagemRepository() retorna o repositório mockado
		BDDMockito.when(postService.getPostagemRepository()).thenReturn(postRepository);
		
	}
	
	@Test
	@DisplayName("Retorna lista de postagens dentro po postList")
	void testFindAll_Sucessful() {
		//criando uma lista de postagem com um elemento
		List<Postagem> postList= List.of(PostagemCreator.criaPostagem());
		
		//quando chamar o medodo findAll vai retornar o postagemPage
				BDDMockito.when(postService.getPostagemRepository().findAll())
							.thenReturn(postList);
		//objetos que vamos comparar 
		String tituloEsperado= PostagemCreator.criaPostagem().getTitulo();
		List<Postagem> respostaFindAll= postagemController.findAll().getBody();
		//realizando os testes 
		Assertions.assertThat(respostaFindAll)
								.isNotNull()
								.isNotEmpty()
								.hasSize(1);
		Assertions.assertThat(respostaFindAll.get(0).getTitulo())
								.isEqualTo(tituloEsperado);
		
	}
	
	@Test
	@DisplayName("busca por id Sucesso")
	void findById_Sucessful() {
		// quando chamar o medodo findAll vai retornar o postagemPage
		BDDMockito.when(postService.getPostagemRepository().findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(PostagemCreator.criaPostagem_Save()));
		ResponseEntity<Postagem> postFindById = postagemController.findById(321L);
		// teste no response entity
		ResponseEntityPostagemTest.testePostagemUnica(postFindById, HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Busca por titulo: Sucesso")
	void findByTitulo() {
		// criando uma lista de postagem com um elemento
		List<Postagem> postList = List.of(PostagemCreator.criaPostagem());
		BDDMockito.when(
				postService.getPostagemRepository().findAllByTituloContainingIgnoreCase(ArgumentMatchers.anyString()))
				.thenReturn(postList);
		//fazendo a requisição no controller 
		ResponseEntity<List<Postagem>> testFindByTitulo= postagemController.findAllByTituloContaining("titulo");
		// realizando os testes
		Assertions.assertThat(testFindByTitulo.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(testFindByTitulo.getBody())
													.isNotNull()
													.isNotEmpty()
													.hasSize(1);
		Assertions.assertThat(testFindByTitulo.getBody().get(0).getTitulo())
								.isEqualTo(PostagemCreator.criaPostagem().getTitulo());
	}
	@Test
	@DisplayName("Busca por Id Inexistente")
	void findByIdInexistente() {
		BDDMockito.when(postService.getPostagemRepository().findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.empty());
		//fazendo a requisição no controller 
		ResponseEntity<Postagem> postFindById= postagemController.findById(3021L);
		// realizando os testes
		ResponseEntityPostagemTest.testePostagemUnicaVazia(postFindById, HttpStatus.NOT_FOUND);
	}
	
	@Test 
	@DisplayName("Save Post")
	void savePost_Sucess() {
		BDDMockito.when(postService.salvar(ArgumentMatchers.any()))
									.thenReturn(PostagemCreator.criaPostagem_Save());
		//fazendo a requisição no controller 
		ResponseEntity<Postagem> postSave= postagemController.adicionarPostagem(PostagemCreator.criaPostagem());
		//realizando os testes 
		ResponseEntityPostagemTest.testePostagemUnica(postSave, HttpStatus.CREATED);
	}
}
