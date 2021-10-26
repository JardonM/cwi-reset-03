package br.com.cwi.reset.jardonmartins.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private StatusCarreira statusCarreira;
    private Integer anoInicioAtividade;

    public Ator() {
    }

    public Ator(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ator ator = (Ator) o;
        return Objects.equals(id, ator.id) && Objects.equals(nome, ator.nome) && Objects.equals(dataNascimento, ator.dataNascimento) && statusCarreira == ator.statusCarreira && Objects.equals(anoInicioAtividade, ator.anoInicioAtividade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataNascimento, statusCarreira, anoInicioAtividade);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}