package br.com.cwi.reset.aula.dois;

public class Pessoa {
    protected String nome;
    protected Integer idade;
    protected Genero genero;


    public void imprimir() {
        System.out.println("Nome: " + this.nome + ".");
        System.out.println("Idade: " + this.idade + ".");
        System.out.println("Genero: " + this.genero.getNome() + ".");
    }
}
