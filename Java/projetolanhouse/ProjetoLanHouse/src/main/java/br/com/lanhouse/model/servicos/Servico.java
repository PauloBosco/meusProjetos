package br.com.lanhouse.model.servicos;
import br.com.lanhouse.model.e.EProduto;

import java.util.ArrayList;

public class Servico {
    private int idServico;
    private  String  cod_servico;
    private ArrayList<EProduto> listaProdutos;
    private ArrayList<Impressoes> listaImpressoes;

    
    public Servico(){
        listaProdutos = new ArrayList<>();
        listaImpressoes = new ArrayList<>();
    }

    public Servico(int idServico, String cod_servico) {
        this.idServico = idServico;
        this.cod_servico = cod_servico;
        listaProdutos = new ArrayList<>();
        listaImpressoes = new ArrayList<>();
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getCod_servico() {
        return cod_servico;
    }

    public void setCod_servico(String cod_servico) {
        this.cod_servico = cod_servico;
    }

    public ArrayList<EProduto> getListaProdutos() {
        return listaProdutos;
    }

    public void addProdutos(EProduto p) {
        listaProdutos.add(p);
    }

    public ArrayList<Impressoes> getListaImpressoes() {
        return listaImpressoes;
    }

    public void addImpressoes(Impressoes i){
        listaImpressoes.add(i);
    }
}
