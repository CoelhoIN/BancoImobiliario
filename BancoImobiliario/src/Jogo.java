import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Jogo {
    Tabuleiro tabuleiro;
    ArrayList<Jogador> jogadores;
    int rodadasRestantes;

    public Jogo(Tabuleiro tabuleiro, ArrayList<Jogador> jogadores, int maxRodadas) {
        this.tabuleiro = tabuleiro;
        this.jogadores = jogadores;
        this.rodadasRestantes = maxRodadas;
    }

    public boolean jogarRodada() {
        if (rodadasRestantes <= 0) {
            System.out.println("Número máximo de rodadas atingido.");
            return false;
        }

        Random dado = new Random();
        for (Jogador jogador : jogadores) {
            if (jogador.saldo > 0) { // Jogador continua no jogo se ainda tiver saldo
                int resultadoDado = dado.nextInt(6) + 1; // número entre 1 e 6
                moverJogador(jogador, resultadoDado);
                interagirComCasa(jogador);
            } else {
                System.out.println(jogador.nome + " está falido e não pode continuar jogando.");
            }
        }
        exibirStatusJogadores();
        rodadasRestantes--;
        return true;
    }

    public void moverJogador(Jogador jogador, int valorDado) {
        // Move o jogador no tabuleiro com base no valor do dado
        for (int i = 0; i < valorDado; i++) {
            jogador.posicaoAtual = jogador.posicaoAtual.proxima;  // Avança no tabuleiro
        }
    }


    public void interagirComCasa(Jogador jogador) {
        Casa casaAtual = jogador.posicaoAtual;

        if (jogador.estaPreso()) {
            jogador.rodadasPreso--;
            System.out.println(jogador.nome + " está preso e ficará por " + jogador.rodadasPreso + " rodada(s).");
            return; // O jogador não pode jogar se está preso
        }

        if (jogador.saldo < 0) {
            System.out.println(jogador.nome + " está com saldo negativo e precisa vender propriedades.");
            while (jogador.saldo < 0 && !jogador.propriedades.isEmpty()) {
                Imovel imovel = jogador.propriedades.remove(0);  // Vende a primeira propriedade
                jogador.saldo += imovel.preco / 2;  // Jogador recebe metade do valor do imóvel
                System.out.println(jogador.nome + " vendeu " + imovel.nome + " por R$" + (imovel.preco / 2));
            }

            if (jogador.saldo < 0) {
                System.out.println(jogador.nome + " não tem mais propriedades e está falido.");
            }
        }

        switch (casaAtual.tipo) {
            case "Imóvel":
                if (casaAtual.imovel.proprietario == null) {
                    // O imóvel está disponível para compra
                    System.out.println(jogador.nome + " parou no imóvel: " + casaAtual.imovel.nome);
                    System.out.println("Preço de compra: R$" + casaAtual.imovel.preco);
                    System.out.println("Deseja comprá-lo? (s/n)");
                    Scanner scanner = new Scanner(System.in);
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        if (jogador.saldo >= casaAtual.imovel.preco) {
                            jogador.saldo -= casaAtual.imovel.preco;
                            casaAtual.imovel.proprietario = jogador;
                            jogador.adicionarPropriedade(casaAtual.imovel);
                            System.out.println("Parabéns, você comprou o imóvel: " + casaAtual.imovel.nome);
                        } else {
                            System.out.println("Saldo insuficiente para comprar o imóvel.");
                        }
                    }
                } else if (!casaAtual.imovel.proprietario.equals(jogador)) {
                    // O imóvel já tem dono, paga-se aluguel
                    System.out.println(jogador.nome + " parou no imóvel de " + casaAtual.imovel.proprietario.nome + " e deve pagar aluguel.");
                    System.out.println("Aluguel: R$" + casaAtual.imovel.aluguel);
                    if (jogador.saldo >= casaAtual.imovel.aluguel) {
                        jogador.saldo -= casaAtual.imovel.aluguel;
                        casaAtual.imovel.proprietario.saldo += casaAtual.imovel.aluguel;
                        System.out.println(jogador.nome + " pagou R$" + casaAtual.imovel.aluguel + " de aluguel para " + casaAtual.imovel.proprietario.nome);
                    } else {
                        System.out.println("Saldo insuficiente para pagar o aluguel.");
                    }
                }
                break;

            case "Imposto":
                double imposto = jogador.saldo * 0.05;
                jogador.saldo -= imposto;
                System.out.println(jogador.nome + " pagou R$" + imposto + " de imposto.");
                break;

            case "Restituição":
                double restit = jogador.salario * 0.1;
                jogador.saldo += restit;
                System.out.println(jogador.nome + " recebeu R$" + restit + " de restituição.");
                break;

            case "Prisao":
                System.out.println(jogador.nome + " foi preso e ficará preso por 3 rodadas.");
                jogador.rodadasPreso = 3;
                break;

            case "Sorte":
                int ganho = new Random().nextInt(1000) + 500; // Ganha entre R$500 e R$1500
                jogador.saldo += ganho;
                System.out.println(jogador.nome + " teve sorte e ganhou R$" + ganho);
                break;

            case "Revés":
                int perda = new Random().nextInt(1000) + 500; // Perde entre R$500 e R$1500
                jogador.saldo -= perda;
                System.out.println(jogador.nome + " teve um revés e perdeu R$" + perda);
                break;

            case "Início":  // Aqui tratamos o caso da casa "Início"
                jogador.saldo += jogador.salario;
                System.out.println(jogador.nome + " passou pela casa Início e recebeu R$" + jogador.salario);
                break;

            default:
                System.out.println(jogador.nome + " está em uma casa especial: " + casaAtual.tipo);
                break;
        }
    }

    public void exibirStatusJogadores() {
        System.out.println("\n--- Status Atualizado dos Jogadores ---");
        for (Jogador jogador : jogadores) {
            // Exibir o nome do jogador
            System.out.println("Jogador: " + jogador.nome);

            // Exibir a posição atual no tabuleiro
            if (jogador.posicaoAtual != null) {
                System.out.println("Posição Atual: " + jogador.posicaoAtual.tipo + " (" + (jogador.posicaoAtual.imovel != null ? jogador.posicaoAtual.imovel.nome : "") + ")");
            } else {
                System.out.println("Posição Atual: Não definida");
            }

            // Exibir o saldo do jogador
            System.out.println("Saldo: R$" + jogador.saldo);

            // Exibir as propriedades do jogador
            jogador.listarPropriedades();

            System.out.println("---------------------------------------");
        }
    }


    public void declararVencedor() {
        Jogador vencedor = null;
        for (Jogador jogador : jogadores) {
            if (vencedor == null || jogador.saldo > vencedor.saldo) {
                vencedor = jogador;
            }
        }
        System.out.println("O vencedor é " + vencedor.nome + " com saldo de R$" + vencedor.saldo);
    }
}


