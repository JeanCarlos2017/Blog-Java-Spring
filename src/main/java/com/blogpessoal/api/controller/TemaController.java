package com.blogpessoal.api.controller;

import java.util.List;

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

import com.blogpessoal.domain.model.Tema;
import com.blogpessoal.domain.service.TemaService;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	@Autowired
	private TemaService temaService;

	@GetMapping("")
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaService.getTemaRepository().findAll());
	}

	@GetMapping("/{temaId}")
	public ResponseEntity<Tema> getById(@PathVariable Long temaId) {
		return temaService.getTemaRepository().findById(temaId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(temaService.getTemaRepository().findAllByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(temaService.save(tema));
	}
	
	@PutMapping("/{temaId}")
	public ResponseEntity<Tema> putTema(@Valid @PathVariable Long temaId, @RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(temaService.put(tema, temaId));
	}
	
	@DeleteMapping("/{temaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTema(@Valid @PathVariable Long temaId) {
		temaService.delete(temaId);
	}

}
