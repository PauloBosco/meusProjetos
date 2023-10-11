package br.com.lanhouse.model.e;

import br.com.lanhouse.model.servicos.Servico;

public enum EProduto {
    COCA("Coca", 5.0),
    PEPSI("Pepsi", 4.0),
    SALGADO("Salgado", 6.0);

    private int idProduto;
    private final String nome;
    private final double valor;
    private Servico servico;

    EProduto(int idProduto, String nome, double valor, Servico servico) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
        this.servico = servico;
    }

    EProduto(String nome, double valor, Servico servico) {
        this.nome = nome;
        this.valor = valor;
        this.servico = servico;
    }

    EProduto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
