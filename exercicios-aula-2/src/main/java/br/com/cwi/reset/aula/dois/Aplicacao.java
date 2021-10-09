package br.com.cwi.reset.aula.dois;

public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Godofredo", 40, 1);
        Diretor diretor2 = new Diretor("José", 58, 3);

        Filme filme1 = new Filme("Emocionante", "Um filme muito emocionante de emoção", 130, 2021, 4.5, diretor1);
        Filme filme2 = new Filme("Filmaço!", "Um filme filmado com filmadora", 15, 2005, 2, diretor2);

        filme1.reproduzirFilme();
        filme2.reproduzirFilme();

    }
}
