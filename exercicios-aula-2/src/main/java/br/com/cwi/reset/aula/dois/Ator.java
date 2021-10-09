package br.com.cwi.reset.aula.dois;

public class Ator {
    private String nome;
    private Integer idade;
    private Integer oscar;
    private Genero genero;

    public Ator(String nome, Integer idade, Integer oscar, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.oscar = oscar;
        this.genero = genero;
    }

    public void imprimirAtor() {
        System.out.println("Nome: " + this.nome + ".");
        System.out.println("Idade: " + this.idade + ".");
        System.out.println("Numero de Oscar: " + this.oscar + ".");
        System.out.println("Genero: " + this.genero + ".");
    }
}
