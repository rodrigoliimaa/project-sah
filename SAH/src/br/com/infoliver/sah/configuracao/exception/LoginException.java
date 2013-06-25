package br.com.infoliver.sah.configuracao.exception;

public class LoginException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private static final String LOGIN_INVALIDO = "Login e/ou Senha Inválidos! Tente Novamente.";

	public LoginException() {
		super(LOGIN_INVALIDO);
	}
}
