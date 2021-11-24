//Implementado por Luís Felipe Dobner Henriques
public class Posicao {
    private int linha;
    private char coluna;
    private boolean ocupada;
    private Peca peca;
    private boolean cor;

    //Encapsulamento
    public Posicao(int linha, char coluna, boolean cor) {

        this.linha = linha;
        this.coluna = coluna;
        this.cor = cor;

    } 

    public int getLinha() {
        return linha;
    }

    public char getColuna() {
        return coluna;
    }

    public boolean getCor() {
        return cor;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public boolean getOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

}
