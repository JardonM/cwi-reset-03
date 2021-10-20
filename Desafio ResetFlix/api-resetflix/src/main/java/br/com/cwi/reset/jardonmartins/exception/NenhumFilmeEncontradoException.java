package br.com.cwi.reset.jardonmartins.exception;

public class NenhumFilmeEncontradoException extends Exception {
    public NenhumFilmeEncontradoException(String tipo) {
        super(String.format("Nenhum filme encontrado com o filtro %s, favor informar outro filtro", tipo));
    }
}
