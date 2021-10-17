package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoInicioAtividadeNaoInformadoException extends CampoNaoInformadoException {
    public AnoInicioAtividadeNaoInformadoException() {
        super("ano início atividade.");
    }
}