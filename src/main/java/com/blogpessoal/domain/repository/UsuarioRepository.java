package com.blogpessoal.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpessoal.domain.model.UsuarioEntidade;


public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, Long>{
	public Optional<UsuarioEntidade> findByNome(String nome);

	public Optional<UsuarioEntidade> findByEmail(String email);

}
