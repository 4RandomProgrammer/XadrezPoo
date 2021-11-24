
//Implementado por Luís Felipe Dobner Henriques
import java.util.InputMismatchException; //Garantir que não de erro nas entradas
import java.util.Scanner; //Scanner, para entradas
import java.io.File; // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class Jogo {
    private int estado;
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private Peca[] pecasBrancas = new Peca[16];
    private Peca[] pecasPretas = new Peca[16];
    private boolean turno; //Dtermina o turno de cada jogador
     

    //Construtores, setters e getters:
    //Códigos para estado:
    // 0 - Jogo normal;
    // 1 - Xeque Branco - Preto;
    // 2 - Xeque Preto -> Branco;
    // 3- Xeque-Mate branco Vence
    // 4 Xeque-Mate Preto vence
    // 5 - Jogo interrompido

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }


    //Construtor incializa as pecas em suas posições respectivas
    public Jogo() {
        //Incializar as Pecas:
        //Peoes
        int i = 0;
        for (; i < 8; i++) {

            pecasBrancas[i] = new Peao(true);
            pecasPretas[i] = new Peao(false);

            tabuleiro.colocar(6, i, pecasPretas[i]);
            tabuleiro.colocar(1, i, pecasBrancas[i]);
        
        }

        //Torres
        for (; i < 10; i++) {
            pecasBrancas[i] = new Torre(true);
            pecasPretas[i] = new Torre(false);

        }
        
        //Cavalos
        for (; i < 12; i++) {
            pecasBrancas[i] = new Cavalo(true);
            pecasPretas[i] = new Cavalo(false);

        }

        //Bispo
        for (; i < 14; i++) {
            pecasBrancas[i] = new Bispo(true);
            pecasPretas[i] = new Bispo(false);

        }

        //Dama
        for (; i < 15; i++) {
            pecasBrancas[i] = new Dama(true);
            pecasPretas[i] = new Dama(false);

        }


        //Rei
        for (; i < 16; i++) {
            pecasBrancas[i] = new Rei(true);
            pecasPretas[i] = new Rei(false);

        }

        int maior = 7;
        int menor = 0;
        i = 8;

        //Brancas
        while( maior > menor) {
            tabuleiro.colocar(0, maior, pecasBrancas[i]);
            tabuleiro.colocar(7, maior, pecasPretas[i]);
            i++;
            tabuleiro.colocar(0, menor, pecasBrancas[i]);
            tabuleiro.colocar(7, menor, pecasPretas[i]);
            i++;
            maior--;
            menor++;
        }
    }

    //Outras funções:
    //Func para o jogador fazer a jogada
    /**
     * 
     * @param cor - Recebe a cor do jogador para saber quais peças ele pode ou não capturar
     * @return - retorna se o jogador fez ou não a jogada para passa para o outro jogador
     */
    public boolean Jogada( boolean cor ){

        int linha, linhaDest;
        char coluna, colunaDest;
        linha = 0;
        coluna = 0;
        boolean podeMovimentar = true;

            do {

                System.out.println("Selecionando Peça ou digite 8 para interromper a partida : ");
                
                linha = entradaLinha();

                if(linha == 8){
                    estado = 5;
                    return false;
                }

                coluna = entradaColuna();

            } while(!tabuleiro.selecionaPeca(linha, coluna, cor)); //verificação para ver se a peça é do jogador que a esta a pedindo.



            do {

                System.out.println("Digite as coordenadas para onde a peça vai se mover ou (-1) para cancelar seleção da peça: ");
                //Usar a mesma função try catch aq
                linhaDest = entradaLinha();

                if(linhaDest == -1){ // Para caso o jogador selecionar a peça errada, ele re-selecionar a peça 
                    return false;
                }

                colunaDest = entradaColuna();

                if(podeMovimentar = !tabuleiro.verMovimento(linha, coluna, linhaDest, colunaDest) || !tabuleiro.comerPeca(cor, linhaDest, colunaDest, false)){
                    System.out.println("A peça não pode se movimentar!");
                }

            }while(podeMovimentar);
            
            //Movendo a peça mesmo
            tabuleiro.comerPeca(cor, linhaDest, colunaDest, true);
            tabuleiro.movePeca(linha, coluna, linhaDest, colunaDest);
            salvarJogadas(linha, coluna, linhaDest, colunaDest);

            // //Verificação para xeque
            if(!cor){ //Tenho que verifcar o rei do oponente
                if(pecasBrancas[15].isVivo()){

                    if(tabuleiro.verificaRei(!cor) == 1) {
                        estado = 2;
                    }
                
                }
                else{
                    estado = 4;

                }
            }
            else{
                if(pecasPretas[15].isVivo()){
                    if(tabuleiro.verificaRei(!cor) == 1) {
                        estado =  1;
                    }
                }
                else{
                    estado = 3;
                }
                
            }
            
            return true;


    }

    //Função para pegar linhas
    private int entradaLinha(){

        Scanner entrada = new Scanner(System.in);
        boolean erroDeEntrada = false; //Para detectar erros de entrada
        int linha = 0;

        do {
            System.out.println("Selecione a LINHA: ");
            
            try {
                linha = entrada.nextInt();
                erroDeEntrada = false;
            } 
            catch (InputMismatchException e) {
                entrada.next();
                erroDeEntrada = true;
                System.out.println("Linha inválida");
            
            }
        } while (erroDeEntrada);

        return linha;
    }

    //Func para as colunas
    private char entradaColuna() {

        Scanner entrada = new Scanner(System.in);
        boolean erroDeEntrada = false; //Para detectar erros de entrada
        char coluna = 'a';

        do {

            System.out.println("Selecione a COLUNA: ");
            
            try {
                coluna = entrada.next().charAt(0);
                erroDeEntrada = false;
            } 
            catch (InputMismatchException e) {
                entrada.next();
                erroDeEntrada = true;
                System.out.println("Linha inválida");
            
            }
        } while (erroDeEntrada);

        return coluna;
    }

    //Função que inicia o jogo 
    //Função que atribui cada jogador a cor selecionada.
    public void inicio( char corEscolhida, boolean carregado ){

        //Colocando as cores para cada jogador
        if(corEscolhida == 'P'){
            jogador1.setCor(false);
            jogador2.setCor(true);
        }
        else if( corEscolhida == 'B'){

            jogador1.setCor(true);
            jogador2.setCor(false);
        }
        else {
            System.out.println("A cor escolhida não é válida");
            return;
        }

        //Jogando as pecas para cada jogador, dependendo da cor escolhida pelo primeiro jogador
        if(jogador1.getCor()){
            jogador1.setPecas(pecasBrancas);
            jogador2.setPecas(pecasPretas);
        }
        else{
            jogador1.setPecas(pecasPretas);
            jogador2.setPecas(pecasBrancas);
        }

        if(!carregado) {
            salvarNomes(corEscolhida);
        }

        turno = jogador1.getCor(); //Se o jogador 1 for o jogador com as peças brancas, ele começa
        // System.out.println(jogador1.getNome() + " a cor é " + jogador1.getCor());
        // System.out.println(jogador2.getNome() + " a cor é " + jogador2.getCor());
    }

    //Func para manter um jogo rodando
    //Função que faz os turnos dos jogadores acontecerem
    public void jogando() {
        
        // System.out.print("\033[H\033[2J");
        // System.out.flush();

        tabuleiro.print();
        //Esses são estados que encerram o jogo
        while(estado != 3 && estado != 4 && estado != 5){
            
            //Jogadas feitas por coordenada.
            if( turno ) {
                System.out.println(jogador1.getNome() + " sua vez: ");
                if(Jogada(jogador1.getCor())) {

                    // System.out.print("\033[H\033[2J");
                    // System.out.flush();

                    tabuleiro.print();

                    //Teste para ver se peças estão vivas
                    // if (turno) {
                        
                    //     for (int i = 0; i < pecasBrancas.length; i++) {
                    //         System.out.println(pecasBrancas[i].getIcone() + ": " + pecasBrancas[i].isVivo());
                    //     }
                        
                    // } else {
                        
                    //     for (int i = 0; i < pecasBrancas.length; i++) {
                    //         System.out.println(pecasPretas[i].getIcone() + ": " + pecasPretas[i].isVivo());
                    //     }
                        
                    // }

                    turno = !turno; //Indica que é turno do próximo jogador
                    //Essa mudança do turno não pode ser fora do  if, pois se não o jogador consegue perder a vez cancelando a peça que ele não quer que seja usada.
                    

                    if(estado == 1){
                        System.out.println("Xeque Branco no Preto");
                    }
                    else if(estado == 2){
                        System.out.println("Xeque Preto no Branco");
                    }
                }
            }
            else{
                System.out.println(jogador2.getNome() + " sua vez: ");
                if(Jogada(jogador2.getCor())) {

                    // System.out.print("\033[H\033[2J");
                    // System.out.flush();

                    tabuleiro.print();

                    //Teste para ver se peças estão vivas
                    // if (turno) {
                        
                    //     for (int i = 0; i < pecasBrancas.length; i++) {
                    //         System.out.println(pecasBrancas[i].getIcone() + ": " + pecasBrancas[i].isVivo());
                    //     }
                        
                    // } else {
                        
                    //     for (int i = 0; i < pecasBrancas.length; i++) {
                    //         System.out.println(pecasPretas[i].getIcone() + ": " + pecasPretas[i].isVivo());
                    //     }
                        
                    // }

                    turno = !turno; //Indica que é turno do próximo jogador


                    if(estado == 1){
                        System.out.println("Xeque Branco no Preto");
                    }
                    else if(estado == 2){
                        System.out.println("Xeque Preto no Branco");
                    }
                }
            }

        }

        switch (estado) {

            case 3: //Caso as brancas vençam
                System.out.println("Jogador das Pecas brancas vence");
            break;

            case 4: //Caso as pretas vençam
                System.out.println("Jogador das Pecas pretas vence");
            break;

            default: //Caso jogo interrompido
                System.out.println("Jogo foi interrompido!");
            break;

        }
    }

    //Função que permite salvar o nome dos jogadores
    private void salvarNomes(char cor){
        try {
            //Craindo o file se ele não existe
            File myFile = new File("jogo.txt");
            if(myFile.exists() == false){
                myFile.createNewFile();
            }
            //Escrevendo nome dos jogadores
            FileWriter myWriter = new FileWriter(myFile);
            myWriter.write(jogador1.getNome() + "\n");
            myWriter.write(jogador2.getNome() + "\n");
            myWriter.write(cor);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Erro!");
        }
        
    }

    //Função que permite salvar as jogadas do jogador
    private void salvarJogadas(int linha, char coluna, int linhaDest, char colunaDest){

        try {
            //Escrevendo as jogadas
            FileWriter escrevendoJogadas = new FileWriter("jogo.txt", true);
            escrevendoJogadas.write("\n" + linha);
            escrevendoJogadas.write("\n" + coluna);
            escrevendoJogadas.write("\n" + linhaDest);
            escrevendoJogadas.write("\n" + colunaDest);
            escrevendoJogadas.close();

        } catch (IOException e) {
            System.out.println("Erro!");
        }
    }

    public void carregarJogo(){
        try {
            //Atribuindo o scanner para o file.
            Scanner leitor = new Scanner(new FileInputStream(new File("jogo.txt")));
            char cor = 0;
            char coluna;
            char colunaDest;
            int linha = 0;
            int linhaDest = 0;


            //Lendo o nome dos 2 jogadores
            String data = leitor.nextLine(); //Carrega o nome do primeiro joagdor
            jogador1.setNome(data);
            data = leitor.nextLine(); //Nome do segundo jogador
            jogador2.setNome(data);

            
            

            //Ler a cor escolhida pelo jogador1
            cor = leitor.nextLine().charAt(0);


            //Iniciando o jogo
            inicio(cor, true);

            //Sei que todas as próximas jogadas dão certo, como fazer para carrega-las...
            while (leitor.hasNextLine()){ //Preciso tratar o último \n, como fazer isso?
                //Ler a linha e coluna inicial e depois ler para onde se move

                try {
                    linha = leitor.nextInt();
                    coluna = leitor.next().charAt(0);
                    linhaDest = leitor.nextInt();
                    colunaDest = leitor.next().charAt(0);
    
                    tabuleiro.comerPeca(turno, linhaDest, colunaDest, true);
                    tabuleiro.movePeca(linha, coluna, linhaDest, colunaDest);

                    turno = !turno;
                    
                } catch (InputMismatchException e) {
                    System.out.println("Entrada fora do padrão! O jogo não pode ser carregado");
                    estado = 5;
                    return;
                }
            }


        } catch (FileNotFoundException e) {
           System.out.println("Erro o arquivo não foi encontrado! ");
        }
    }

}
