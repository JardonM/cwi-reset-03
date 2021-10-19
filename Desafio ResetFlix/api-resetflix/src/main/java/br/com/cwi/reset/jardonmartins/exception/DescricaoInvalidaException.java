package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DescricaoInvalidaException extends Exception{
    public DescricaoInvalidaException(final String tipo) {
        super(String.format("A descrição deve ser mais completa.", tipo));
    }
}
