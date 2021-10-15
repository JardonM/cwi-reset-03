package br.com.cwi.reset.jardonmartins.exception;

public class ConsultaIdInvalidoException extends Exception {
    public ConsultaIdInvalidoException(String tipo, Integer id) {
        super(String.format("Nenhum %s encontrado com o parâmetro id=%d, favor verifique os parâmetros informados.", tipo, id));
    }
}