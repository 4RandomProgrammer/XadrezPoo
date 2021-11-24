

public class Cavalo extends Peca {

    public Cavalo(boolean Cor) {
        super();

        this.cor = Cor;

        if(getCor()){
            this.icone = 'C';
        }
        else {
            this.icone = 'c';
        }
    
    }

    @Override
    public char desenho() {
        return getIcone();
    }

    @Override
    /**
     * @param - linha e coluna de onde a peca esta e linha e coluna para onde a peca vai
     * @return - retorna se consegue fazer o movimento
     */
    public boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino) {
        boolean coluna = colunaOrigem - colunaDestino == 2 || colunaOrigem - colunaDestino == -2;
        boolean linha = linhaOrigem - linhaDestino == 2 || linhaOrigem - linhaDestino == -2;

        if(linha && (colunaOrigem - colunaDestino == 1 || colunaOrigem - colunaDestino == -1)) {
            return true;
        }
        else if(coluna && (linhaOrigem - linhaDestino == 1 || linhaOrigem - linhaDestino == -1)){
            return true;
        }
        else {
            return false;
        }
    }

}
