package br.com.cwi.reset.projeto1;

import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
        int soma = 0;
        for (int i = 0; i < numeros.toArray().length; i++) {
            soma += numeros.get(i);
        }
        return soma;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double soma = 0;
        for (int i = 0; i < numeros.toArray().length; i++) {
            soma += numeros.get(i);
        }
        return (soma / numeros.toArray().length);
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        int maior = numeros.get(0);
        for (int i = 0; i < numeros.toArray().length; i++){
            if (numeros.get(i) > maior) {
                maior = numeros.get(i);
            }
        }
        return maior;
    }

    public String obterPalavraInvertida(String palavra) {
        String invertido = "";
        for (int i = palavra.length() -1; i >= 0; i--) {
            invertido += palavra.charAt(i);
        }
        return invertido;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        int aux;
        for (int i = 0; i < numeros.size() -1; i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(i) > numeros.get(j)) {
                    aux = numeros.get(j);
                    numeros.set(j,numeros.get(i));
                    numeros.set(i, aux);
                }
            }
        }
        return numeros;
    }
}

