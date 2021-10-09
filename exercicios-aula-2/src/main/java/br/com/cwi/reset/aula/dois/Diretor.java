package br.com.cwi.reset.aula.dois;

public class Diretor {
    private String nome;
    private int idade;
    private int quantFilmesDirigidos;

    public Diretor(String nome, int idade, int quantFilmesDirigidos) {
        this.nome = nome;
        this.idade = idade;
        this.quantFilmesDirigidos = quantFilmesDirigidos;
    }

    public String getNome() {
        return this.nome;
    }
}
