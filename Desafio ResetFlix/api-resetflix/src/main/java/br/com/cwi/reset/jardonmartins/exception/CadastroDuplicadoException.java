package br.com.cwi.reset.jardonmartins.exception;

public class CadastroDuplicadoException extends Exception {
    public CadastroDuplicadoException(String tipo, String nome) {
        super(String.format("Já existe um %s cadastrado para o nome %s.", tipo, nome));
    }
}