package br.com.cwi.reset.jardonmartins.request;

import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;

import java.time.LocalDate;

public class AtorRequest {
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
    private LocalDate anoInicioAtividade;

    public AtorRequest(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, LocalDate anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
