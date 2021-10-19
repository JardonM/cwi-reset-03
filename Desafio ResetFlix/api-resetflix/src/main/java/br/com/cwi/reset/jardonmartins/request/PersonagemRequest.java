package br.com.cwi.reset.jardonmartins.request;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.TipoAtuacao;

public class PersonagemRequest {
    private Ator ator;
    private String nomePersonagem;
    private String descricaoPersonagem;
    private TipoAtuacao tipoAtuacao;


    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public void setDescricaoPersonagem(String descricaoPersonagem) {
        this.descricaoPersonagem = descricaoPersonagem;
    }

    public TipoAtuacao getTipoAtuacao() {
        return tipoAtuacao;
    }

    public void setTipoAtuacao(TipoAtuacao tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }
}
