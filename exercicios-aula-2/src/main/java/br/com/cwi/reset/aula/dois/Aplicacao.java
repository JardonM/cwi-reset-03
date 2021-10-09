package br.com.cwi.reset.aula.dois;

import java.time.LocalDate;

public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Godofredo", LocalDate.of(1900, 8, 9), 1, Genero.MASCULINO);
       Diretor diretor2 = new Diretor("José", LocalDate.of(2000, 10, 9), 3, Genero.MASCULINO);

        Filme filme1 = new Filme("Emocionante", "Um filme muito emocionante de emoção", 130, 2021, 4.5, diretor1);
       Filme filme2 = new Filme("Filmaço!", "Um filme filmado com filmadora", 15, 2005, 2, diretor2);

       filme1.reproduzirFilme();
       filme2.reproduzirFilme();


        Ator ator1 = new Ator ("Atorinho", LocalDate.of(2000, 10, 9), 1, Genero.MASCULINO);
      Ator atora = new Ator ("Atriza", LocalDate.of(2000, 10, 9), 6, Genero.NAO_BINARIO);

        ator1.imprimir();
        atora.imprimir();
       diretor1.imprimir();

        try {
            filme1.avaliacaoValida();
        } catch (AvaliacaoForaDoPadraoException exception) {
            System.out.println(exception.getMessage());
        }



    }
}
