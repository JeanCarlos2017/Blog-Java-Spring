package com.blogpessoal.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.domain.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao); 
	public Tema findByDescricao(String descricao);

}
