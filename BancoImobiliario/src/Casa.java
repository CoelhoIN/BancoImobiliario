class Casa {
    String tipo;
    Imovel imovel; // Só será preenchido se for do tipo "Imóvel"
    Casa proxima;

    public Casa(String tipo, Imovel imovel) {
        this.tipo = tipo;
        this.imovel = imovel;
    }
}

