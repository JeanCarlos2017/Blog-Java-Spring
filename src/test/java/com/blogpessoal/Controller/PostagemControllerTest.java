package com.blogpessoal.Controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.blogpessoal.api.controller.PostagemController;
import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;
import com.blogpessoal.domain.service.CadastroPostagemService;
import com.blogpessoal.util.PostagemCreator;

@ExtendWith(SpringExtension.class)
public class PostagemControllerTest {
	@InjectMocks //quero testar a classe em si
	private PostagemController postagemController;
	@Mock 
	private CadastroPostagemService postService;
	@Mock 
	private PostagemRepository postRepository;
	
	@BeforeEach
	void setUp() {
		List<Postagem> postList= List.of(PostagemCreator.criaPostagem());
		//quando chamar o postService.getPostagemRepository() retorna o repositório mockado
		BDDMockito.when(postService.getPostagemRepository()).thenReturn(postRepository);
		//quando chamar o medodo findAll vai retornar o postagemPage
		BDDMockito.when(postService.getPostagemRepository().findAll())
					.thenReturn(postList);
	}
	
	@Test
	@DisplayName("Retorna lista de postagens dentro po postList")
	void testFindAll_Sucessful() {
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

}
