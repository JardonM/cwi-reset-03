package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Pessoa {
    protected String nome;
    protected LocalDate dataNasc;
    protected Genero genero;


    public Pessoa(String nome, LocalDate dataNasc, Genero genero) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.genero = genero;
    }

    public int calcularIdade() {
        LocalDateTime dataAtual = LocalDateTime.now();
        int idade = dataAtual.getYear() - this.dataNasc.getYear();
        return idade;
    }



    public void imprimir() {
        System.out.println("Nome: " + this.nome + ".");
        System.out.println("Idade: " + this.calcularIdade() + ".");
        System.out.println("Genero: " + this.genero.getNome() + ".");
    }
}
