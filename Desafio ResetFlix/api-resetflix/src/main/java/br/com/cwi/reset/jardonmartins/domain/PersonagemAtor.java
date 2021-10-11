package br.com.cwi.reset.jardonmartins.domain;

import java.time.LocalDate;

public class PersonagemAtor extends Ator{
    private Ator ator;
    private String descricaoPersonagem;
    TipoAtuacao tipoAtuacao;

    public PersonagemAtor(Integer id, String nome, LocalDate dataNascimento, LocalDate anoInicioAtividade) {
        super(id, nome, dataNascimento, anoInicioAtividade);
    }
}
