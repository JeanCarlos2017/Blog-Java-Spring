package com.blogpessoal.domain.exception;

public class CadastroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CadastroException (String message) {
		super(message);
	}
}
