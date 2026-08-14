package br.org.hbr.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
