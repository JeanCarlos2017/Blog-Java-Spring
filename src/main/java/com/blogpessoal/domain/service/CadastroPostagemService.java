package com.blogpessoal.domain.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;


@Service
public class CadastroPostagemService {
	@Autowired
	private PostagemRepository postagemRepository;

	public Postagem salvar(Postagem postagem) {
		//duas postagem pode ter o mesmo titulo?
		//Postagem post= postagemRepository.findByTitulo(postagem.getTitulo());
		return postagemRepository.save(postagem);
	}

	public void excluir(@Valid Long id) {
		postagemRepository.deleteById(id);		
	}
	
}
