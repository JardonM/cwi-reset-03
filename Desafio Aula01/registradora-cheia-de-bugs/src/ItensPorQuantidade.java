public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 4;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static int itensEmEstoque (String item) { //Retorna a quantidade disponível em estoque

        if (item.equals("pao")) {
            return pao / 60;
        }
        if (item.equals("torta")) {
            return torta * 16;
        }
        if (item.equals("sanduiche")) {
            return sanduiche;
        }
        if (item.equals("leite")) {
            return leite;
        }
        if (item.equals("cafe")) {
            return cafe;
        }

        return 0;
    }

    public static void baixaDeEstoque (String item, int quantidade) { //Retira do estoque a quantia que foi vendida ao cliente
        if (item.equals("pao")) {
            pao -= (quantidade * 60);
        }
        if (item.equals("torta")) {
            torta = (torta * 16) - quantidade;
        }
        if (item.equals("sanduiche")) {
           sanduiche -= quantidade;
        }
        if (item.equals("leite")) {
            leite -= quantidade;
        }
        if (item.equals("cafe")) {
            cafe -= quantidade;
        }
        if (QuantidadeMinimaItem.precisaReposicao(item)) { //Verifica após a venda se o estoque precisa de reposição
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                } else {
                    ReposicaoCozinha.reporItem(item);
                }
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
            }
        }

    }


}
