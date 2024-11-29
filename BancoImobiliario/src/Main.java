import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Tabuleiro tabuleiro = new Tabuleiro();
    private static ArrayList<Jogador> jogadores = new ArrayList<>();
    private static int saldoInicial;
    private static int salarioJogador;
    private static int maxRodadas;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean jogoIniciado = false;

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar Imóvel");
            System.out.println("2. Listar Imóveis");
            System.out.println("3. Cadastrar Jogador");
            System.out.println("4. Listar Jogadores");
            System.out.println("5. Definir Configurações Iniciais");
            System.out.println("6. Iniciar Jogo");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrando imóveis automaticamente...");
                    tabuleiro.configurarTabuleiro();  // Chama a função para configurar o tabuleiro
                    System.out.println("Imóveis cadastrados e tabuleiro configurado.");
                    break;
                case 2:
                    listarImoveis();
                    break;
                case 3:
                    cadastrarJogador(scanner);
                    break;
                case 4:
                    listarJogadores();
                    break;
                case 5:
                    definirConfiguracoesIniciais(scanner);
                    break;
                case 6:
                    if (jogadores.size() >= 2 && tabuleiro.tamanho >= 10) {
                        iniciarJogo(scanner);
                        jogoIniciado = true;
                    } else {
                        System.out.println("Erro: O jogo precisa de pelo menos 2 jogadores e 10 imóveis cadastrados.");
                    }
                    break;
                case 7:
                    System.out.println("Saindo do jogo.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (jogoIniciado) break;
        }
    }


    private static void listarImoveis() {
        System.out.println("\n=== IMÓVEIS CADASTRADOS ===");
        Casa atual = tabuleiro.inicio;
        do {
            if (atual.imovel != null) {
                System.out.println("Imóvel: " + atual.imovel.nome + " | Preço: R$" + atual.imovel.preco + " | Aluguel: R$" + atual.imovel.aluguel);
            }
            atual = atual.proxima;
        } while (atual != tabuleiro.inicio);

        // Perguntar se o jogador deseja editar ou remover um imóvel
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDeseja editar ou remover algum imóvel? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do imóvel que deseja editar ou remover: ");
            String nomeImovel = scanner.nextLine();

            // Buscar o imóvel
            Casa casa = buscarCasa(nomeImovel);

            if (casa != null && casa.imovel != null) {
                System.out.println("Imóvel encontrado: " + casa.imovel.nome);
                System.out.println("Escolha uma opção:");
                System.out.println("1. Editar Imóvel");
                System.out.println("2. Remover Imóvel");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        editarImovel(scanner, casa);
                        break;
                    case 2:
                        removerImovel(casa);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Imóvel não encontrado.");
            }
        }
    }

    private static void editarImovel(Scanner scanner, Casa casa) {
        System.out.println("\n=== EDITAR IMÓVEL ===");

        // Editando nome do imóvel
        System.out.print("Digite o novo nome do imóvel (atual: " + casa.imovel.nome + "): ");
        String novoNome = scanner.nextLine();
        casa.imovel.nome = novoNome;

        // Editando preço do imóvel
        System.out.print("Digite o novo preço do imóvel (atual: R$" + casa.imovel.preco + "): ");
        int novoPreco = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        casa.imovel.preco = novoPreco;

        System.out.println("Imóvel atualizado com sucesso!");
    }

    private static void removerImovel(Casa casa) {
        // Remover o imóvel do tabuleiro
        casa.imovel = null;
        System.out.println("Imóvel removido com sucesso!");
    }

    private static Casa buscarCasa(String nomeImovel) {
        Casa atual = tabuleiro.inicio;
        do {
            if (atual.imovel != null && atual.imovel.nome.equalsIgnoreCase(nomeImovel)) {
                return atual;
            }
            atual = atual.proxima;
        } while (atual != tabuleiro.inicio);
        return null; // Retorna null caso não encontre o imóvel
    }


    private static void cadastrarJogador(Scanner scanner) {
        if (jogadores.size() >= 6) {
            System.out.println("Erro: Já existem 6 jogadores cadastrados.");
            return;
        }

        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine();

        // Inicializar o jogador com saldo inicial e definir a posição na casa "Início"
        Jogador jogador = new Jogador(nome, saldoInicial, salarioJogador);
        jogador.posicaoAtual = tabuleiro.inicio;  // Posição inicial na casa "Início"

        jogadores.add(jogador);
        System.out.println("Jogador cadastrado com sucesso. Posição inicial: Início");
    }


    private static void listarJogadores() {
        System.out.println("\n=== JOGADORES CADASTRADOS ===");
        for (Jogador jogador : jogadores) {
            System.out.println("Nome: " + jogador.nome + " | Saldo: R$" + jogador.saldo + " | Salário: R$" + jogador.salario);
        }

        // Perguntar se o jogador deseja editar ou remover um jogador
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDeseja editar ou remover algum jogador? (s/n): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do jogador que deseja editar ou remover: ");
            String nomeJogador = scanner.nextLine();

            // Buscar o jogador
            Jogador jogador = buscarJogador(nomeJogador);

            if (jogador != null) {
                System.out.println("Jogador encontrado: " + jogador.nome);
                System.out.println("Escolha uma opção:");
                System.out.println("1. Editar Jogador");
                System.out.println("2. Remover Jogador");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        editarJogador(scanner, jogador);
                        break;
                    case 2:
                        removerJogador(jogador);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Jogador não encontrado.");
            }
        }
    }

    private static void editarJogador(Scanner scanner, Jogador jogador) {
        System.out.println("\n=== EDITAR JOGADOR ===");

        // Editando nome do jogador
        System.out.print("Digite o novo nome do jogador (atual: " + jogador.nome + "): ");
        String novoNome = scanner.nextLine();
        jogador.nome = novoNome;

        // Editando saldo do jogador
        System.out.print("Digite o novo saldo do jogador (atual: R$" + jogador.saldo + "): ");
        int novoSaldo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        jogador.saldo = novoSaldo;

        // Editando salário do jogador
        System.out.print("Digite o novo salário do jogador (atual: R$" + jogador.salario + "): ");
        int novoSalario = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        jogador.salario = novoSalario;

        System.out.println("Jogador atualizado com sucesso!");
    }

    private static void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
        System.out.println("Jogador removido com sucesso!");
    }

    private static Jogador buscarJogador(String nome) {
        for (Jogador jogador : jogadores) {
            if (jogador.nome.equalsIgnoreCase(nome)) {
                return jogador;
            }
        }
        return null; // Retorna null caso não encontre o jogador
    }


    private static void definirConfiguracoesIniciais(Scanner scanner) {
        System.out.print("Definir saldo inicial dos jogadores: ");
        saldoInicial = scanner.nextInt();
        System.out.print("Definir salário dos jogadores: ");
        salarioJogador = scanner.nextInt();
        System.out.print("Definir número máximo de rodadas: ");
        maxRodadas = scanner.nextInt();

        for (Jogador jogador : jogadores) {
            jogador.saldo = saldoInicial;
            jogador.salario = salarioJogador;
        }
        System.out.println("Configurações atualizadas com sucesso.");
    }

    private static void iniciarJogo(Scanner scanner) {
        Jogo jogo = new Jogo(tabuleiro, jogadores, maxRodadas);

        // Laço de rodadas
        while (jogo.jogarRodada()) {
            // Perguntar se o jogador quer negociar imóveis
            System.out.println("\nDeseja negociar imóveis nesta rodada? (s/n): ");
            String negociarResposta = scanner.next();
            scanner.nextLine();  // Consumir a nova linha

            if (negociarResposta.equalsIgnoreCase("s")) {
                negociarImoveis(scanner);  // Chama o método de negociação de imóveis
            }

            // Perguntar se o jogador quer continuar para a próxima rodada
            System.out.println("\nDeseja continuar para a próxima rodada? (s/n): ");
            String continuar = scanner.next();
            if (!continuar.equalsIgnoreCase("s")) {
                break;
            }
        }

        // Ao fim do jogo, declarar o vencedor
        System.out.println("O jogo terminou.");
        jogo.declararVencedor();
    }

    private static void negociarImoveis(Scanner scanner) {
        System.out.println("\n=== NEGOCIAR IMÓVEIS ===");

        listarJogadoresDisponiveis();
        System.out.print("Digite o nome do comprador: ");
        String nomeComprador = scanner.nextLine();
        System.out.print("Digite o nome do vendedor: ");
        String nomeVendedor = scanner.nextLine();

        Jogador comprador = buscarJogadorNegocios(nomeComprador);
        Jogador vendedor = buscarJogadorNegocios(nomeVendedor);

        if (comprador == null || vendedor == null) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        listarPropriedades(vendedor); // Listar propriedades do vendedor
        System.out.print("Digite o nome do imóvel a ser vendido: ");
        String nomeImovel = scanner.nextLine();

        Imovel imovel = buscarPropriedade(vendedor, nomeImovel);

        if (imovel != null && comprador.saldo >= imovel.preco) {
            comprador.saldo -= imovel.preco;
            vendedor.saldo += imovel.preco;
            vendedor.propriedades.remove(imovel);
            comprador.propriedades.add(imovel);
            imovel.proprietario = comprador;
            System.out.println(comprador.nome + " comprou " + imovel.nome + " de " + vendedor.nome);
        } else {
            System.out.println("Transação não pode ser realizada.");
        }
    }

    // Metodo auxiliar para listar jogadores
    public static void listarJogadoresDisponiveis() {
        System.out.println("Jogadores disponíveis para negociação:");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.nome);
        }
    }

    // Metodo auxiliar para buscar um jogador pelo nome
    public static Jogador buscarJogadorNegocios(String nome) {
        for (Jogador jogador : jogadores) {
            if (jogador.nome.equalsIgnoreCase(nome)) {
                return jogador;
            }
        }
        return null;
    }

    // Metodo auxiliar para listar as propriedades de um jogador
    public static void listarPropriedades(Jogador jogador) {
        System.out.println(jogador.nome + " possui as seguintes propriedades:");
        for (Imovel imovel : jogador.propriedades) {
            System.out.println(imovel.nome + " (Preço de compra: R$" + imovel.preco + ")");
        }
    }

    // Metodo auxiliar para buscar uma propriedade pelo nome
    public static Imovel buscarPropriedade(Jogador jogador, String nomeImovel) {
        for (Imovel imovel : jogador.propriedades) {
            if (imovel.nome.equalsIgnoreCase(nomeImovel)) {
                return imovel;
            }
        }
        return null;
    }
}

