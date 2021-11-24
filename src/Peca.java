//Implementado por Luís Felipe Dobner Henriques
public abstract class Peca {
    protected boolean cor; //True - Branco, False - Preto
    protected boolean vivo;
    protected char icone;

    public Peca(){
        setVivo(true);
    }

    public boolean getCor() {
        return cor;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public char getIcone() {
        return icone;
    }

    public abstract char desenho(); //Função de desenho de cada peca
    public abstract boolean checaMovimento(int linhaOrigem, char colunaOrigem, int linhaDestino, char colunaDestino);

}
