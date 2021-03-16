package com.blogpessoal.domain.service;

import java.util.List;
import java.util.Optional;

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

	public List<Postagem> findAll(){
		return this.postagemRepository.findAll();
	}
	
	public Optional<Postagem> findById(long postId) {
		return this.postagemRepository.findById(postId);
		
	}
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo){
		return this.postagemRepository.findAllByTituloContainingIgnoreCase(titulo);
	}
	
}
