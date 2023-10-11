package br.com.lanhouse.model.servicos;

public class Impressoes {
    private int idImpressoes;
    private Servico idServico;
    private String nome;
    private double preco;

    public Impressoes() {
    }

    public Impressoes(int idImpressoes, Servico idServico, String nome, double preco) {
        this.idImpressoes = idImpressoes;
        this.idServico = idServico;
        this.nome = nome;
        this.preco = preco;
    }

    public int getIdImpressoes() {
        return idImpressoes;
    }

    public void setIdImpressoes(int idImpressoes) {
        this.idImpressoes = idImpressoes;
    }

    public Servico getIdServico() {
        return idServico;
    }

    public void setIdServico(Servico idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
