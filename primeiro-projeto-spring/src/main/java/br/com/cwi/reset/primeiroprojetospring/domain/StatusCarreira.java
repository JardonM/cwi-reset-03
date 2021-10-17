package br.com.cwi.reset.primeiroprojetospring.domain;

public enum StatusCarreira {
    EM_ATIVIDADE("Em atividade."),
    APOSENTADO("Aposentado.");

    private String statusCarreira;

    StatusCarreira(String statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

    public String getStatusCarreira() {
        return this.statusCarreira;
    }
}
