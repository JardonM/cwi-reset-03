package br.com.cwi.reset.jardonmartins.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa {

    public Diretor(Integer id, String nome, LocalDate dataNascimento, LocalDate anoInicioAtividade) {
        super(id, nome, dataNascimento, anoInicioAtividade);
    }
}