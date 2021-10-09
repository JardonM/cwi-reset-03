package br.com.cwi.reset.aula.dois;

public class Diretor {
    private String nome;
    private Integer idade;
    private Integer quantFilmesDirigidos;

    public Diretor(String nome, Integer idade, Integer quantFilmesDirigidos) {
        this.nome = nome;
        this.idade = idade;
        this.quantFilmesDirigidos = quantFilmesDirigidos;
    }

    public String getNome() {
        return this.nome;
    }
}
