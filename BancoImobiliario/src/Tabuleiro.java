class Tabuleiro {
    Casa inicio;
    int tamanho;

    public Tabuleiro() {
        this.inicio = null;
        this.tamanho = 0;
    }

    // Metodo para adicionar casas ao tabuleiro
    public void adicionarCasa(String tipo, Imovel imovel) {
        Casa novaCasa = new Casa(tipo, imovel);

        if (inicio == null) {
            // A primeira casa do tabuleiro é a casa "Início"
            inicio = novaCasa;
            inicio.proxima = inicio;  // Torna a lista circular
        } else {
            // Inserir a nova casa no final da lista (antes de voltar ao "Início")
            Casa atual = inicio;
            while (atual.proxima != inicio) {
                atual = atual.proxima;
            }
            atual.proxima = novaCasa;
            novaCasa.proxima = inicio;  // Faz a nova casa apontar para o início, mantendo a circularidade
        }
        tamanho++;
    }

    public void configurarTabuleiro() {
        // Primeiro adicionei a casa de "Início"
        adicionarCasa("Início", null);

        // Adicionei imóveis automaticamente
        adicionarCasa("Imóvel", new Imovel("Casa do Bosque", 200000, 1100));
        adicionarCasa("Imóvel", new Imovel("Apartamento Central", 350000, 1800));
        adicionarCasa("Imóvel", new Imovel("Vila das Flores", 400000, 2200));
        adicionarCasa("Imóvel", new Imovel("Pousada da Praia", 500000, 2700));
        adicionarCasa("Imóvel", new Imovel("Mansão da Colina", 600000, 3300));
        adicionarCasa("Imóvel", new Imovel("Residência do Lago", 450000, 2500));
        adicionarCasa("Imóvel", new Imovel("Cobertura Diamante", 700000, 3700));
        adicionarCasa("Imóvel", new Imovel("Edifício Horizonte", 550000, 2900));
        adicionarCasa("Imóvel", new Imovel("Chácara do Sol", 300000, 1600));
        adicionarCasa("Imóvel", new Imovel("Fazenda Boa Vista", 250000, 1300));

        // Adicionei duas casas de imposto
        adicionarCasa("Imposto", null);
        adicionarCasa("Imposto", null);

        // Adicionei uma casa de restituição
        adicionarCasa("Restituição", null);

        // Adicionei uma casa de prisão
        adicionarCasa("Prisao", null);

        // Adicionei casas de sorte e revés
        adicionarCasa("Sorte", null);
        adicionarCasa("Revés", null);

    }

}
