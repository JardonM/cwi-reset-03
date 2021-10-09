package br.com.cwi.reset.aula.dois;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracao;
    private Integer anoLancamento;
    private double avaliacao;
    private Diretor diretorFilme;

    public Filme (String nome, String descricao, Integer duracao, Integer anoLancamento, double avaliacao, Diretor diretorFilme) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretorFilme = diretorFilme;
    }

    public void reproduzirFilme() {
        System.out.println("Nome do filme: " + this.nome + ".");
        System.out.println("Descricao: " + this.descricao + ".");
        System.out.println("Duração: " + this.duracao + " minutos.");
        System.out.println("Diretor: " + this.diretorFilme.getNome() + ".");
    }


}
