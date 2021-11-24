//Implementado por Luís Felipe Dobner Henriques

public class Rei extends Peca {


    public Rei(boolean Cor) {
        super();

        this.cor = Cor;

        if(getCor()){
            this.icone = 'R';
        }
        else {
            this.icone = 'r';
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


        boolean coluna = colunaOrigem - colunaDestino <= 1 && colunaOrigem - colunaDestino >= -1;
        boolean linha = linhaOrigem - linhaDestino <= 1 && linhaOrigem - linhaDestino >= -1;

        if ( linha && coluna ) {
            return true;
        }
        else {
            return false;
        }

    }
}
