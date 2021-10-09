package br.com.cwi.reset.aula.dois;

public class Diretor {
    private String nome;
    private Integer idade;
    private Integer quantFilmesDirigidos;
    private Genero genero;

    public Diretor(String nome, Integer idade, Integer quantFilmesDirigidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantFilmesDirigidos = quantFilmesDirigidos;
        this.genero = genero;
    }

    public String getNome() {
        return this.nome;
    }

    public void imprimirDiretor() {
        System.out.println("Nome: " + this.nome + ".");
        System.out.println("Idade: " + this.idade + ".");
        System.out.println("Quantidade de filmes dirigidos: " + this.quantFilmesDirigidos + ".");
        System.out.println("Genero: " + this.genero.getNome() + ".");
    }
}
