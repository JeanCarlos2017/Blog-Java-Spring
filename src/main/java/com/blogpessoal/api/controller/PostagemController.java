package com.blogpessoal.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class PostagemController {
	@GetMapping("/")
	public String testeServidor() {
		return "Servidor funcionando";
	}

}
