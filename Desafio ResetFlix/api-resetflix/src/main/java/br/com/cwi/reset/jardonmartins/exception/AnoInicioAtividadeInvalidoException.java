package br.com.cwi.reset.jardonmartins.exception;

public class AnoInicioAtividadeInvalidoException extends Exception {
    public AnoInicioAtividadeInvalidoException(String tipo) {
        super(String.format("Ano de início de atividade inválido para o %s cadastrado.", tipo));
    }
}