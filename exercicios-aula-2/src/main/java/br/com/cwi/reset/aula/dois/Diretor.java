package br.com.cwi.reset.aula.dois;

public class Diretor extends Pessoa{


    private Integer quantFilmesDirigidos;


    public Diretor(String nome, Integer idade, Integer quantFilmesDirigidos, Genero genero) {
        super(nome, idade, genero);
        this.quantFilmesDirigidos = quantFilmesDirigidos;

    }

    public String getNome() {
        return this.nome;
    }

}
