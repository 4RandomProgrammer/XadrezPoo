//Implementado por Luís Felipe Dobner Henriques

public class Tabuleiro {
    private Posicao[][] Tabuleiro = new Posicao[8][8];


    //Função para colocar manualmente as pecas no tabuleiro
    //i = linha onde vai ser colocada. j = coluna onde a peca é colocada.
    public void colocar(int i, int j, Peca peca) {
        try {
            Tabuleiro[i][j].setPeca(peca);
            Tabuleiro[i][j].setOcupada(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("A peça não pode ser colocada ai.");
        }

    }

    public void print() {
        System.out.print(" ");
        int linha = 0;
        
        //Printando as colunas
        for (char i = 'a'; i < 'i'; i++) {
            System.out.print( "   " + i );
        }
        
        System.out.println();

        for(int i = 0; i < 8; i++, linha++) {
            //Printando as linhas
            System.out.print(linha + " ");
            
            for (int j = 0; j < 8; j++) {
                if(!Tabuleiro[i][j].getOcupada()) {

                    if(this.Tabuleiro[i][j].getCor()) {
                        
                        System.out.print( "| " + " " + " ");
                        
                    }
                    else {
                        System.out.print( "| " + "X" + " ");
                    }

                }
                else {
                    System.out.print("| " + Tabuleiro[i][j].getPeca().desenho() + " ");
                }

            }
            
            System.out.println("|");
                
        }

    }
    
    //i = linha onde esta a peça, j = coluna onde esta a peça, cor = representa a cor da peça que esta se querendo selecionar
    public boolean selecionaPeca(int i, char j, boolean cor){
        
        try {
                        
            if(Tabuleiro[i][(int)(j - 97)].getOcupada() && Tabuleiro[i][(int)(j - 97)].getPeca().getCor() == cor){
                System.out.println("Essa peça é sua! Peça selecionada com sucesso!");
                return true;
            }
            else{
                System.out.println("Lugar vazio e/ou Peca não é sua!");
                return false;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição fora do tabuleiro!");
            return false;
        }
    }
    //Inicializa o tabuleiro e saus posições de acordo com a cor
    public Tabuleiro() {
        //Iniciar todas as posicoes aqui
        boolean cor = true;
        for (int i = 0; i < 8 ; i++) {
            char a = 'a'; //Valor usado para colocar o valor da coluna
            for (int j = 0; j < 8; j++,a++) {
                this.Tabuleiro[i][j] = new Posicao(i,a,cor);
                this.Tabuleiro[i][j].setOcupada(false);
                    cor = !cor;
            }
        
            cor = !cor; //Para fazer a cor de cada parte do tabuleiro
        }


    }

    //Preciso fazer a verificação se a peça P para onde ela possa se movimentar:
    //Recebe de parametros a linha inical e coluna inical e a linha final e coluna final, respectivamente representados por:
    //i,j,x,y
    public boolean verMovimento(int i, char j, int x, char y){
        

        try {

            Peca P = Tabuleiro[i][(int)(j - 97)].getPeca();

            if(P.checaMovimento(Tabuleiro[i][(int)j - 97].getLinha(), Tabuleiro[i][j - 97].getColuna(), Tabuleiro[x][y - 97].getLinha(), Tabuleiro[x][y - 97].getColuna())){
                //Verificação das posições
                if(P instanceof Cavalo){ //Cavalo pode pular peças, então não precisa verificar o caminho que ele esta fazendo, apenas a posição final
                    return true;
                }
                //Cuidei disso aqui, pois não queria fazer a função de comer receber a coordenada incial
                else if(P instanceof Peao){
                    if(P.getCor()){
                        //Verificar se esta ocupada
                        if(i - x == -1 && (j - y == -1 || j - y == 1 )) { //Movimentação de comer peao branco
                            if ( Tabuleiro[x][y - 97].getOcupada() &&  Tabuleiro[x][y - 97].getPeca().getCor() != P.getCor()) {
                                return true;
                            }
                            else {

    
                                return false;
                            }
                        }
                        else{ //Significa que não é a posição de comer
                            if(Tabuleiro[x][y - 97].getOcupada()){
                                return false;
                            }

                            return true;
                        }
                    }
                    else{
                        if(i - x == 1 && (j - y == -1 || j - y == 1 )) { //Movimentação de comer peao preto
                            if ( Tabuleiro[x][y - 97].getOcupada() &&  Tabuleiro[x][y - 97].getPeca().getCor() != P.getCor()) {
                                return true;
                            }
                            else {
                                return false;
                            }
                        }
                        else{ //Significa que não é a posição de comer
                            if(Tabuleiro[x][y - 97].getOcupada()){
                                
                                return false;
                            }

                            return true;
                        }
                    }

                }
                else{
                    //Como eu vejo se todas as posições que as posições estão livres da peça até a o ponto que ela quer ir?
                    //Andando reto nas colunas
                    if(i == x){
                        //Caso coluna inicial maior que a final
                        if( j > y){
                            while(j > y + 1){
                                j--;
                                if(Tabuleiro[i][j - 97].getOcupada()){
                                    return false;
                                }

                            }

                            return true;
                        }
                        else{
                            //Caso coluna inicial menor que a final
                            while(j  < y - 1 ){
                                j++;
                                if(Tabuleiro[i][j - 97].getOcupada()){
                                    return false;
                                }

                            }

                            return true;
                        }
                    }
                    //Andando reto, nas linhas
                    else  if( y == j ){

                        //Caso linha incial maior ou igual a linha final
                        if( i >= x){
                            while(i > x + 1){
                                i--;
                                if(Tabuleiro[i][j - 97].getOcupada()){
                                    return false;
                                }

                            }
                            return true;
                        }
                        else{
                            //Casol linha inicial menor que a final
                            while(i < x - 1 ){
                                i++;
                                if(Tabuleiro[i][j - 97].getOcupada()){
                                    return false;
                                }

                            }

                            return true;
                        }

                    }
                    //Verificação para diagonal
                    else {
                        //Se a linha inicial for maior que a final
                        if( i > x ) {
                            //Se a coluna inicial for maior que a final
                            if(j > y){
                                while(j > y + 1 && i > x + 1){
                                    i--;
                                    j--;

                                    if(Tabuleiro[i][j - 97].getOcupada()){
                                        return false;
                                    }

                                }

                                return true;
                            }
                            else {
                                //Se a coluna inical for maior que a final
                                while(j < y - 1 && i > x + 1){
                                    i--;
                                    j++;

                                    if(Tabuleiro[i][j - 97].getOcupada()){
                                        return false;
                                    }

                                }

                                return true;
                            }
                        }
                        else { //Caso a linha inicial seja menor que a final
                            //Coluna inicial maior que a final
                            if(j > y){
                                while(j > y + 1 && i < x - 1){
                                    i++;
                                    j--;

                                    if(Tabuleiro[i][j - 97].getOcupada()){
                                        return false;
                                    }

                                }
                                return true;
                            }

                            //Coluna inicial menor que a final
                            else {
                                while(j < y - 1 && i < x - 1){
                                    i++;
                                    j++;

                                    if(Tabuleiro[i][j - 97].getOcupada()){
                                        return false;   
                                    }

                                }

                                return true;
                            }

                        }
                    }
                } 
            }
            else{

                return false;
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição fora do tabuleiro!");
            return false;
        }

    }

    //Quando ja esta tudo certo a peca se move
    public void movePeca(int i, char j, int x, char y) {

        try {
        //XY recebe a peca
            Tabuleiro[x][y - 97].setPeca(Tabuleiro[i][j - 97].getPeca());
            Tabuleiro[x][y - 97].setOcupada(true);
        
        //IJ é de onde a peca se move, pos inicial
            Tabuleiro[i][j - 97].setOcupada(false);
            Tabuleiro[i][j - 97].setPeca(null);

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("Posição fora do Tabuleiro");

        }

    }

    //Param
    //Cor - cor da peça que vai comer
    //int x - linha destino da peça
    //char y - coluna destino da peça
    //boolean podeComer - serve para indicar se apenas esta verifcando ou se esta comendo uma peça
    public boolean comerPeca(boolean cor, int x, char y, boolean podeComer){

        try {

            if(Tabuleiro[x][y - 97].getOcupada()){
            
                if(Tabuleiro[x][y - 97].getPeca().getCor() != cor){
                    
                    //Jogador sabe que perdeu a peca
                    if(podeComer) { //Fiz para fazer a verificação do rei e não comê-lo direto
                        Tabuleiro[x][y - 97].getPeca().setVivo(false);
                        Tabuleiro[x][y - 97].setPeca(null);
                    }
    
                    return true;
    
                }
                else{
    
                    return false;
                }
            }
            else{
                return true;
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

    //Como implementar o check e o check mate, no estado de check o jogador só pode mexer o rei.
    //A partir o tabuleiro tem que sempre saber onde esta o rei?
    //Eu tenho que verificar se em algum momento a peça pode matar o rei.
    //Oq eu posso ver é verificar todo o tabuleiro, e cada peça e ver se ela pode matar o rei, se tiver uma que sim, muda o estado para
    //xeque
    //Quem tem que saber a posição do rei? O tab ou o jogo? Procurando ele
    //Recebe a cor do rei que eu quero ver se esta em cheque,
    public int verificaRei( boolean cor ){
        //Primeiro Loop Encontrar o rei
        boolean procuraRei = false;
        int reiL = 0;
        int reiC = 0;
        for (; reiL < 8; reiL++) {
            for (reiC = 0; reiC < 8; reiC++) {
                if(Tabuleiro[reiL][reiC].getOcupada()){

                    if((Tabuleiro[reiL][reiC].getPeca() instanceof Rei) && (Tabuleiro[reiL][reiC].getPeca().getCor() == cor)){
                        procuraRei = true;
                        break;
                    }
                }
            }

            if(procuraRei) {
                break;
            }
        }

        //Aqui seria para verificar se todas as peças podem comer o rei.
        //Posso fazer essa verificação aqui?
        for (int i = 0; i < 8; i++) {
            for (int k = 0; k < 8; k++) {
                if(Tabuleiro[i][k].getOcupada()){
                    //Seria verificação se uma peça pode comer o rei
                    //Tenho que calcular todas as movimentação possiveis do rei, pq dai eu sei que é cheque ou cheque mate.
                    //Como fazer isso?
                    Peca comp = Tabuleiro[i][k].getPeca(); //Pegando uma peca para ver se ela chega no rei
                    if( cor != comp.getCor() ){ //Vendo se a cor é diferente
                        //testar para ver se chega no rei, se chega muda o estado para xeque
                        if(verMovimento( i, (char)(k + 97) , reiL, (char)(reiC + 97) )  && comerPeca(comp.getCor(), reiL, (char)(reiC + 97), false)){
                            return 1;
                        }
                    }
                }
            }
        }

        return 0;
    }


}
