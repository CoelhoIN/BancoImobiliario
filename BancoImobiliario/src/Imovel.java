class Imovel {
    String nome;
    int preco;
    int aluguel;
    Jogador proprietario;

    public Imovel(String nome, int preco, int aluguel) {
        this.nome = nome;
        this.preco = preco;
        this.aluguel = aluguel;
        this.proprietario = null; // No início, sem dono
    }
}
