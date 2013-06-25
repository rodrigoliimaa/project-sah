package br.com.infoliver.sah.configuracao.exception;

public class DAOException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DAOException() {
    }

    public DAOException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public DAOException(String mensagem) {
        super(mensagem);
    }

    public DAOException(Throwable causa) {
        super(causa);
    }
}