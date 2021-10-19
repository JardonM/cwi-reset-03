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

    public Filme(Integer id, String nome, LocalDate anoLancamento, String capaFilme, List<Genero> generos, Diretor diretor, Estudio estudio, List<PersonagemAtor> personagens, String resumo) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.diretor = diretor;
        this.estudio = estudio;
        this.personagens = personagens;
        this.resumo = resumo;
    }
}
