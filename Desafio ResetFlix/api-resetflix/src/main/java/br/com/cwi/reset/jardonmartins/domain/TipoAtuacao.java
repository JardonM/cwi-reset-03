package br.com.cwi.reset.jardonmartins.domain;

public enum TipoAtuacao {
    PROTAGONISTA("Protagonista"),
    COADJUVANTE("Coadjuvante"),
    ANTAGONISTA("Antagonista(vilão)");

    private String tipoAtuacao;

    TipoAtuacao(String tipoAtuacao) {
        this.tipoAtuacao = tipoAtuacao;
    }


}
