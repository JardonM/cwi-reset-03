package br.com.cwi.reset.primeiroprojetospring.domain;


import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;

public class Filme {
    private Integer id;
    private String nome;
    private String capaFilme;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLancamento;


    public String getNome() {
        return this.nome;
    }

    public Integer getId() {
        return id;
    }


    public String getCapaFilme() {
        return capaFilme;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setCapaFilme(String capaFilme) {
        this.capaFilme = capaFilme;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
