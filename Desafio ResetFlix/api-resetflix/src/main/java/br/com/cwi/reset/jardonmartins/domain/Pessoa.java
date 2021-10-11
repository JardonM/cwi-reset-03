package br.com.cwi.reset.jardonmartins.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Pessoa {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private LocalDate anoInicioAtividade;

    public int calcularIdade() {
        LocalDateTime dataAtual = LocalDateTime.now();
        int idade = dataAtual.getYear() - this.dataNascimento.getYear();
        return idade;
    }

    public Pessoa(Integer id, String nome, LocalDate dataNascimento, LocalDate anoInicioAtividade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setAnoInicioAtividade(LocalDate anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
