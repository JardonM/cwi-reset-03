package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class PersonagemAtor extends Ator{
    private Ator ator;
    private String descricaoPersonagem;
    private TipoAtuacao tipoAtuacao;


    public PersonagemAtor(Integer id, String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        super(id, nome, dataNascimento, anoInicioAtividade, statusCarreira);
    }
}
