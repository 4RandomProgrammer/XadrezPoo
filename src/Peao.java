
//Implementado por Luís Felipe Dobner Henriques

public class Peao extends Peca {

    private boolean movimentou; //Diz se o peão ja se movimentou ou não

    public Peao(boolean Cor) {
        super();

        movimentou = false;


        this.cor = Cor;

        if(getCor()){
            this.icone = 'P';
        }
        else {
            this.icone = 'p';
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
        switch(icone){

            case 'P':
                if (movimentou) {

                    if (  (  linhaOrigem - linhaDestino == -1) && (colunaOrigem - colunaDestino <= 1 && colunaOrigem - colunaDestino >= -1 ) ) {
                        movimentou = true;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    //Movimentação basica se não tiver peça
                    boolean linha = linhaDestino - linhaOrigem != 0 && ( linhaOrigem - linhaDestino == -2);
                    boolean coluna = colunaOrigem - colunaDestino == 0;
                    if ( linha && coluna ) {
                        movimentou = true;
                        return true;
                    }
                    else if( ( linhaOrigem - linhaDestino == -1) && (colunaOrigem - colunaDestino <= 1 && colunaOrigem - colunaDestino >= -1 ) ) {
                        movimentou = true;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
        
            case 'p':
                if (movimentou) {

                    if (  (  linhaOrigem - linhaDestino == 1) && (colunaOrigem - colunaDestino <= 1 && colunaOrigem - colunaDestino >= -1 ) ) {
                        movimentou = true;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    //Movimentação basica se não tiver peça
                    boolean linha = linhaDestino - linhaOrigem != 0 && ( linhaOrigem - linhaDestino == 2);
                    boolean coluna = colunaOrigem - colunaDestino == 0;
                    if ( linha && coluna ) {
                        movimentou = true;
                        return true;
                    }
                    else if( ( linhaOrigem - linhaDestino == 1) && (colunaOrigem - colunaDestino <= 1 && colunaOrigem - colunaDestino >= -1 ) ) {
                        movimentou = true;
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            



        }
        //Caso o icone seja diferente dos casos mencionados
        return false;
    }
}
