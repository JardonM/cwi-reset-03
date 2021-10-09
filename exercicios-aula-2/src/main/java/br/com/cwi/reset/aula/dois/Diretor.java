package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;

public class Diretor extends Pessoa{


    private Integer quantFilmesDirigidos;


    public Diretor(String nome, LocalDate dataNasc, Integer quantFilmesDirigidos, Genero genero) {
        super(nome, dataNasc, genero);
        this.quantFilmesDirigidos = quantFilmesDirigidos;

    }

    public String getNome() {
        return this.nome;
    }

}
