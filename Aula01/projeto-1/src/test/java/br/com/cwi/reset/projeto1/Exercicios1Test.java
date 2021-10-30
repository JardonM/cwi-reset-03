package br.com.cwi.reset.projeto1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Exercicios1Test {
    private Exercicios1 exercicios = new Exercicios1();

    @Test
    public void somaComCincoInteirosPositivos() {
        List<Integer> listaInteirosPositivos = Arrays.asList(5, 6, 10, 15, 4);
        Integer expected = 40;

        Integer result = exercicios.somarLista(listaInteirosPositivos);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void somaComQuatroInteirosPositivosE1Negativo() {
        List<Integer> listaInteirosPositivosENegativo = Arrays.asList(5, 6, 10, 15, -8);
        Integer expected = 28;

        Integer result = exercicios.somarLista(listaInteirosPositivosENegativo);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void somaComTresNegativos() {
        List<Integer> listaInteirosNegativos = Arrays.asList(-5,-6,-7);
        Integer expected = -18;

        Integer result = exercicios.somarLista(listaInteirosNegativos);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void somaComZeros() {
        List<Integer> listaZeros = Arrays.asList(0,0,0,0,0);
        Integer expected = 0;

        Integer result = exercicios.somarLista(listaZeros);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void somaComListaVazia() {
        List<Integer> listaVazia = Arrays.asList();
        Integer expected = 0;

        Integer result = exercicios.somarLista(listaVazia);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void mediaCincoInteirosPositivos() {
        List<Integer> listaInteirosPositivos = Arrays.asList(5, 6, 10, 15, 4);
        Double expected = 8.0;

        Double result = exercicios.calcularMedia(listaInteirosPositivos);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void mediaQuatroInteirosPositivosE1Negativo() {
        List<Integer> listaInteirosPositivosENegativo = Arrays.asList(5, 6, 10, 15, -8);
        Double expected = 5.6;

        Double result = exercicios.calcularMedia(listaInteirosPositivosENegativo);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void mediaTresNegativos() {
        List<Integer> listaInteirosNegativos = Arrays.asList(-5,-6,-7);
        Double expected = -6.0;

        Double result = exercicios.calcularMedia(listaInteirosNegativos);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void mediaComZeros() {
        List<Integer> listaZeros = Arrays.asList(0,0,0,0,0);
        Double expected = 0.0;

        Double result = exercicios.calcularMedia(listaZeros);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void mediaComListaVazia() {
        List<Integer> listaVazia = Arrays.asList();


        double result = exercicios.calcularMedia(listaVazia);
        boolean isNaN = Double.isNaN(result);
        Assertions.assertTrue(isNaN);
    }

    @Test
    public void palavraInvertidaAbacate() {
        String palavra = "abacate";
        String expected = "etacaba";

        String result = exercicios.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void palavraInvertidaBanana() {
        String palavra = "banana";
        String expected = "ananab";

        String result = exercicios.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void palavraInvertidaPessego() {
        String palavra = "pessego";
        String expected = "ogessep";

        String result = exercicios.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);

    }

    @Test
    public void palavraInvertidaMorango() {
        String palavra = "morango";
        String expected = "ognarom";

        String result = exercicios.obterPalavraInvertida(palavra);

        Assertions.assertEquals(expected, result);

    }
}
