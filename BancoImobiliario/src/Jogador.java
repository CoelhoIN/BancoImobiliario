import java.util.ArrayList;

class Jogador {
    String nome;
    int saldo;
    Casa posicaoAtual;
    ArrayList<Imovel> propriedades;
    int salario;
    int rodadasPreso;

    public Jogador(String nome, int saldoInicial, int salario) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.salario = salario;
        this.propriedades = new ArrayList<>();
        this.posicaoAtual = null; // Definida ao iniciar o jogo
        this.rodadasPreso = 0;
    }

    public boolean estaPreso() {
        return rodadasPreso > 0;
    }

    public void adicionarPropriedade(Imovel imovel) {
        propriedades.add(imovel);
        System.out.println(nome + " adquiriu o imóvel: " + imovel.nome);
    }

    public void listarPropriedades() {
        if (propriedades.isEmpty()) {
            System.out.println(nome + " não possui propriedades.");
        } else {
            System.out.println("Propriedades de " + nome + ":");
            for (Imovel imovel : propriedades) {
                System.out.println(" - " + imovel.nome);
            }
        }
    }
}

