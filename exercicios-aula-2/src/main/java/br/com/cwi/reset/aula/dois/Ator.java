package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;

public class Ator extends Pessoa{

    private Integer oscar;


    public Ator(String nome, LocalDate dataNasc, Integer oscar, Genero genero) {
        super(nome, dataNasc, genero);
        this.oscar = oscar;

    }

}
