package br.com.cwi.reset.jardonmartins;

import java.time.LocalDate;
import java.util.List;

public class Filme {
    private Integer id;
    private String nome;
    private LocalDate anoLancamento;
    private String capaFilme;
    private List<Genero> generos;
    private Diretor diretor;
    private List<PersonagemAtor> personagens;
    private String resumo;
}
