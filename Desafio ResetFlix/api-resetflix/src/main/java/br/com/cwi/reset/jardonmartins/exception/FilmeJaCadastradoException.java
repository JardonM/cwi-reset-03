package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmeJaCadastradoException extends Exception {
    public FilmeJaCadastradoException() {
        super("Filme ja cadastrado, verifique os dados e tente novamente.");
    }
}
