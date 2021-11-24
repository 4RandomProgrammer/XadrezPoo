//Implementado por Luís Felipe Dobner Henriques
public class Jogador {
    private String nome;
    private boolean cor; //True - Branco, False - Preto
    private Peca[] pecas = new Peca[16];

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public boolean getCor() {
        return cor;
    }

    public void setPecas(Peca[] pecas) {
        this.pecas = pecas;
    }

}
