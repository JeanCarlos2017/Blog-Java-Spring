package com.blogpessoal.domain.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.domain.exception.CadastroException;
import com.blogpessoal.domain.exception.EntidadeNaoEncontradaException;
import com.blogpessoal.domain.model.Tema;
import com.blogpessoal.domain.repository.TemaRepository;

@Service
public class TemaService {
	@Autowired
	private TemaRepository temaRepository;

	public TemaRepository getTemaRepository() {
		return temaRepository;
	}

	public Tema save(@Valid Tema tema) {
		Tema descricaoExiste = temaRepository.findByDescricao(tema.getDescricao());
		// não permite salvar temas com as mesma descrição
		if (descricaoExiste == null) {
			temaRepository.save(tema);
			return tema;
		} else {
			throw new CadastroException("Já existe um tema com essa descrição!");
		}
	}

	public Tema put(Tema tema, Long id_tema) {
		Optional<Tema> findTema = temaRepository.findById(id_tema);
		if (findTema.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					"Não existe uma entidade com o id passado! Por favor verifique a requisição e tente novamente");
		}else {
			tema.setId(id_tema);
			return temaRepository.save(tema);
		}
	}
	
	public void delete(Long id_tema) {
		Optional<Tema> findTema = temaRepository.findById(id_tema);
		if (findTema.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					"Não existe uma entidade com o id passado! Por favor verifique a requisição e tente novamente");
		}else {
			temaRepository.deleteById(id_tema);
		}
	}

}
