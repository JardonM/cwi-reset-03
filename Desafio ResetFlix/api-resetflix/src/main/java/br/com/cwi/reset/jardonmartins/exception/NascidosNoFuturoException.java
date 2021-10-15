package br.com.cwi.reset.jardonmartins.exception;

public class NascidosNoFuturoException extends Exception {
    public NascidosNoFuturoException(String tipo) {
        super(String.format("Não é possível cadastrar %s não nascidos.", tipo));
    }
}