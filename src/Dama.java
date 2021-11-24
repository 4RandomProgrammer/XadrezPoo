
//Implementado por Luís Felipe Dobner Henriques
public class Dama extends Peca {


    public Dama(boolean Cor) {
        super();

        this.cor = Cor;

        if(getCor()){
            this.icone = 'D';
        }
        else {
            this.icone = 'd';
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

        boolean coluna = colunaOrigem == colunaDestino;
        boolean linha = linhaOrigem == linhaDestino;

        if ( linha || coluna ) {
            return true;
        }
        else if ( linhaOrigem - linhaDestino == colunaOrigem - colunaDestino ) {
            return true;
        }
        else if ( linhaOrigem - linhaDestino == -(colunaOrigem - colunaDestino) ) {
            return true;
        }
        else {
            return false;
        }

    }
}
