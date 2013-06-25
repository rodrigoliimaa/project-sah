package br.com.infoliver.sah.configuracao.exception;

public class BOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BOException() {
    }

    public BOException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public BOException(String mensagem) {
        super(mensagem);
    }

    public BOException(Throwable causa) {
        super(causa);
    }
}