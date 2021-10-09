package br.com.cwi.reset.aula.dois;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não binário");

    private String nome;

    Genero(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
