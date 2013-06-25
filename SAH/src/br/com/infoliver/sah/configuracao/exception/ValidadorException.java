package br.com.infoliver.sah.configuracao.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidadorException extends BOException {
	private static final long serialVersionUID = 1L;

	private Set<ConstraintViolation<Object>> violacoes;
	
	public ValidadorException(String mensagem,Set<ConstraintViolation<Object>> violacoes) {
		super(mensagem);
		this.violacoes = violacoes; 
	}

	public Set<ConstraintViolation<Object>> getViolacoes() {
		return violacoes;
	}
	
	
}