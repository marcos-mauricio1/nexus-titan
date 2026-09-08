package com.nexustitan.nexustitanapi.exception;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String message) {
        super(message); // Repassa a mensagem para a classe-mãe RuntimeException
    }

}
