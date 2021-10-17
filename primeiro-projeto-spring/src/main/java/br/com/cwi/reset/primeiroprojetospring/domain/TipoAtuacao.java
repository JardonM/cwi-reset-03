package br.com.cwi.reset.primeiroprojetospring.domain;

public enum TipoAtuacao {
    PROTAGONISTA("Protagonista"),
    COADJUVANTE("Coadjuvante"),
    ANTAGONISTA("Antagonista(vilão)");

    private String tipoAtuacao;

    TipoAtuacao(String tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }


}
