package br.com.cwi.reset.aula.dois;

public class Pessoa {
    protected String nome;
    protected Integer idade;
    protected Genero genero;

    public Pessoa(String nome, Integer idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public void imprimir() {
        System.out.println("Nome: " + this.nome + ".");
        System.out.println("Idade: " + this.idade + ".");
        System.out.println("Genero: " + this.genero.getNome() + ".");
    }
}
