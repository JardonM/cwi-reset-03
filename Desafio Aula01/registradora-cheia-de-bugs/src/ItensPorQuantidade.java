public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 4;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static int itensEmEstoque (String item) {

        if (item.equals("pao")) {
            return pao;
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

    public static void baixaDeEstoque (String item, int quantidade) {
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

    }


}
