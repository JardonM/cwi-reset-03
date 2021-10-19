package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoLancamentoInvalidoException extends Exception {
    public AnoLancamentoInvalidoException(String tipo) {
        super(String.format("Ano de lancamento inválido para o %s cadastrado.", tipo));
    }
}
