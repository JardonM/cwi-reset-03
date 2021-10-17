package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa {

    public Diretor(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        super(id, nome, dataNascimento, anoInicioAtividade);
    }
}
