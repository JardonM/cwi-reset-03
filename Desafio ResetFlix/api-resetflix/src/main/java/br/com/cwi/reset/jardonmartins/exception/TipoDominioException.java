package br.com.cwi.reset.jardonmartins.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public enum TipoDominioException {

    ATOR("ator", "atores"),
    DIRETOR("diretor", "diretores"),
    ESTUDIO("estúdio", "estúdios"),
    PERSONAGEM("personagem", "personagens");

    private final String singular;
    private final String plural;

    TipoDominioException(final String singular, final String plural) {
        this.singular = singular;
        this.plural = plural;
    }

    public String getSingular() {
        return singular;
    }

    public String getPlural() {
        return plural;
    }
}