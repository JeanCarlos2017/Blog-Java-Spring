package com.blogpessoal.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.domain.exception.CadastroException;
import com.blogpessoal.domain.exception.EntidadeNaoEncontradaException;
import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.repository.PostagemRepository;


@Service
public class CadastroPostagemService {
	@Autowired
	private PostagemRepository postagemRepository;
	
	private boolean verificaCamposPostagem(Postagem post) {
		//faço a verificação de titulo e texto
		if(post.getTitulo() == null || post.getTitulo().isEmpty())
			throw new CadastroException("título não pode ser nulo e/ou vazio!");
		if(post.getTexto() == null || post.getTexto().isEmpty()) 
			throw new CadastroException("texto não pode ser nulo e/ou vazio!");
		
		return true;
	}
	public Postagem salvar(Postagem postagem) {
		if(this.verificaCamposPostagem(postagem)) {
			return postagemRepository.save(postagem);
		}
		return null;
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
	
	public Postagem alteraPostagem(Postagem postagem, long idPostagem) {
		if(this.postagemRepository.existsById(idPostagem)) {
			postagem.setId(idPostagem);
			return this.postagemRepository.save(postagem);
		}else {
			throw new EntidadeNaoEncontradaException
					("Não existe uma entidade com o id passado! Por favor verifique a requisição e tente novamente");
		}
	}
	
	public void deletePostagem(long idPost) {
		if(this.postagemRepository.existsById(idPost)) {
			this.postagemRepository.deleteById(idPost);
		}else {
			throw new EntidadeNaoEncontradaException
					("Não existe uma entidade com o id passado, portanto não foi feita a remoção! "
							+ "Por favor verifique a requisição e tente novamente");
		}
	}
}
