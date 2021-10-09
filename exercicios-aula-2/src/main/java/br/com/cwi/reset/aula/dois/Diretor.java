package br.com.cwi.reset.aula.dois;

public class Diretor extends Pessoa{


    private Integer quantFilmesDirigidos;


    public Diretor(String nome, Integer idade, Integer quantFilmesDirigidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantFilmesDirigidos = quantFilmesDirigidos;
        this.genero = genero;
    }

    public String getNome() {
        return this.nome;
    }

}
