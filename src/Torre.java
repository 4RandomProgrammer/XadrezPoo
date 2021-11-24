//Implementado por Luís Felipe Dobner Henriques
public class Torre extends Peca {


    public Torre(boolean Cor) {
        super();

        this.cor = Cor;

        if(getCor()){
            this.icone = 'T';
        }
        else {
            this.icone = 't';
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

        if(linhaDestino == linhaOrigem && colunaDestino == colunaOrigem) {
            return false;
        }

        boolean linha = linhaOrigem - linhaDestino == 0;
        boolean coluna = colunaOrigem - colunaDestino == 0;

        if( linha || coluna) {
            return true;
        }
        else {
            return false;
        }


    }


}
