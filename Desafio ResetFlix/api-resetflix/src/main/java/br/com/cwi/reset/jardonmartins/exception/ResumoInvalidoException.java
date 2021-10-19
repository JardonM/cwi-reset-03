package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResumoInvalidoException extends Exception {
    public ResumoInvalidoException(final String tipo) {
        super(String.format("O resumo deve ser mais completo.", tipo));
    }
}
