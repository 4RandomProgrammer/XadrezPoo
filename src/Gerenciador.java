//Implementado por Luís Felipe Dobner Henriques
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gerenciador {
    public static void main(final String[] args)  {

        String nome1;
        String nome2;
        char cor;
        Jogo novoJogo = new Jogo();
        int carregar = 0;
        boolean prosseguir = false;

        System.out.println("Bem vindo ao Jogo de Xadrez!");

        final Scanner nomes = new Scanner(System.in);

        System.out.println("Deseja Carregar um jogo? Digite 1 para sim, 2 para não");
        do {
            try {

                carregar = nomes.nextInt();

                prosseguir = false;

                if(carregar != 1 && carregar != 2){
                    System.out.println("Entrada inválida tente novamente!");
                    prosseguir = true;
                }
                
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida tente novamente");
                nomes.nextLine();
                prosseguir = true;
            }

            

        } while (prosseguir);

        if(carregar == 1) {
            novoJogo.carregarJogo();

            novoJogo.jogando();
                
        }

        else{

            System.out.print("Digite o nome do primeiro jogador: ");
            nomes.nextLine();
            nome1 = nomes.nextLine();

            System.out.print("Digite o nome do segundo jogador: ");
            
            nome2 = nomes.nextLine();

            //Colocando o nome de cada jogador
            novoJogo.getJogador1().setNome(nome1);
            novoJogo.getJogador2().setNome(nome2);

            //Eu poderia colocar try catch aqui?1
            
            do {

                System.out.println(nome1 + " escolha a cor de suas peças (P/B):");
                cor = nomes.next().charAt(0);
                
                if(cor != 'B' && cor != 'P') {
                    System.out.println("Cor Invalida! Tente de novo!");
                }

            } while (cor != 'B' && cor != 'P');

            novoJogo.inicio( cor, false );
            
            novoJogo.jogando();

        }

        nomes.close();
    }

    
}
