package br.com.cwi.reset.primeiroprojetospring.domain;

public enum StatusAtividade {
    EM_ATIVIDADE("Em atividade."),
    ENCERRADO("Encerrado.");

    private String statusAtividade;

    StatusAtividade(String statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getStatusAtividade() {
        return this.statusAtividade;
    }
}
