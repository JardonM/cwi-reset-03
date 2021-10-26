package br.com.cwi.reset.jardonmartins.request;

import br.com.cwi.reset.jardonmartins.domain.StatusAtividade;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EstudioRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;
    @NotNull
    @NotBlank
    @NotEmpty
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @NotBlank
    @NotEmpty
    private LocalDate dataCriacao;
    @NotNull
    @NotBlank
    @NotEmpty
    private StatusAtividade statusAtividade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }
}
