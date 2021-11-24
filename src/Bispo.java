//Implementado por Luís Felipe Dobner Henriques
public class Bispo extends Peca{

    public Bispo(boolean Cor) {
        super();

        this.cor = Cor;

        if(getCor()){
            this.icone = 'B';
        }
        else {
            this.icone = 'b';
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

        if ( linhaOrigem - linhaDestino == colunaOrigem - colunaDestino ) {
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
