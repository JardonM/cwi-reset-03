package br.com.cwi.reset.jardonmartins.request;

import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtorRequest {
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
    private StatusCarreira statusCarreira;
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

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
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

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
