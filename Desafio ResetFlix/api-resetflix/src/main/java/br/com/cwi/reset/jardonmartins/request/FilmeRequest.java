package br.com.cwi.reset.jardonmartins.request;

import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.domain.Genero;
import br.com.cwi.reset.jardonmartins.domain.PersonagemAtor;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class FilmeRequest {
    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;

    @NotNull
    @NotBlank
    @NotEmpty
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Integer anoLancamento;

    @NotNull
    @NotBlank
    @NotEmpty
    private String capaFilme;
    @NotNull
    @NotBlank
    @NotEmpty
    private List<Genero> generos;
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer idDiretor;
    @NotNull
    @NotBlank
    @NotEmpty
    private Integer idEstudio;
    @NotNull
    @NotBlank
    @NotEmpty
    private List<PersonagemRequest> personagens;
    @NotNull
    @NotBlank
    @NotEmpty
    private String resumo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public void setCapaFilme(String capaFilme) {
        this.capaFilme = capaFilme;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor) {
        this.idDiretor = idDiretor;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    public List<PersonagemRequest> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemRequest> personagens) {
        this.personagens = personagens;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}
