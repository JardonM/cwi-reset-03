package br.com.cwi.reset.jardonmartins.exception;

public class DescricaoInvalidaException extends Exception{
    public DescricaoInvalidaException(final String tipo) {
        super(String.format("A descrição deve ser mais completa.", tipo));
    }
}
