package br.com.cwi.reset.jardonmartins.request;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiretorRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;
    @NotNull
    @NotBlank
    @NotEmpty
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer anoInicioAtividade;



    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
