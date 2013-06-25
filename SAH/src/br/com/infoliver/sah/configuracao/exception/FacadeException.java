package br.com.infoliver.sah.configuracao.exception;

public class FacadeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FacadeException() {
	}

	public FacadeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public FacadeException(String mensagem) {
		super(mensagem);
	}

	public FacadeException(Throwable causa) {
		super(causa);
	}
}