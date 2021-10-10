package br.com.cwi.reset.jardonmartins;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Pessoa {
    private Integer id;
    private String nome;
    private LocalDate dataNasc;
    private LocalDate anoInicioAtividade;

    public int calcularIdade() {
        LocalDateTime dataAtual = LocalDateTime.now();
        int idade = dataAtual.getYear() - this.dataNasc.getYear();
        return idade;
    }
}
