package com.blogpessoal.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.domain.model.Postagem;
import com.blogpessoal.domain.service.CadastroPostagemService;



@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	
	@Autowired
	private CadastroPostagemService cadastroPostagem;
	
	@GetMapping("")
	public ResponseEntity<List<Postagem>> findAll() {
		return ResponseEntity.ok(cadastroPostagem.getPostagemRepository().findAll());
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Postagem> findById(@PathVariable Long postId) {
		Optional<Postagem> post = cadastroPostagem.getPostagemRepository().findById(postId);
		if (post.isPresent()) {
			return ResponseEntity.ok(post.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findAllByTituloContaining(@PathVariable String titulo) {
		return ResponseEntity.ok(cadastroPostagem.getPostagemRepository().findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Postagem adicionarPostagem(@Valid @RequestBody Postagem postagem) {
		return cadastroPostagem.salvar(postagem);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Postagem> alteraPostagem(@Valid @PathVariable Long id, @RequestBody Postagem post) {
		if(cadastroPostagem.getPostagemRepository().existsById(id)) {
			//id passado é válido
			post.setId(id); //passo o valor para o post 
			cadastroPostagem.salvar(post);
			return ResponseEntity.ok(post);
		}else {
			//não encontrou o ID 
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePostagem(@Valid @PathVariable Long id) {
		if(cadastroPostagem.getPostagemRepository().existsById(id)) {
			//id passado é válido
			cadastroPostagem.excluir(id);
			return ResponseEntity.noContent().build();
		}else {
			//não encontrou o ID 
			return ResponseEntity.notFound().build();
		}
	}

}
