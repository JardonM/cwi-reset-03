package br.com.cwi.reset.jardonmartins.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

public class Filme {
    private Integer id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate anoLancamento;

    private String capaFilme;
    private List<Genero> generos;
    private Diretor diretor;
    private Estudio estudio;
    private List<PersonagemAtor> personagens;
    private String resumo;

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

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
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

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public List<PersonagemAtor> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(List<PersonagemAtor> personagens) {
        this.personagens = personagens;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}
