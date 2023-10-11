package br.com.lanhouse.model.pessoas;

import br.com.lanhouse.dao.impl.ServicoDAO;
import br.com.lanhouse.dao.impl.UsoDAO;
import br.com.lanhouse.model.e.EProduto;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.servicos.Impressoes;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.model.servicos.Uso;
import java.sql.SQLException;

public class Cliente extends Usuario {

    private Uso usoTotal;
    public Cliente() {
    }

    public Cliente(int idUsuario, String nome, String login, String senha, ETipoCliente tipoCliente) {
        super(idUsuario, nome, login, senha, tipoCliente);
    }

    public Cliente(int idUsuario, String nome, String login, String senha, ETipoCliente tipoCliente, Uso usoTotal) {
        super(idUsuario, nome, login, senha, tipoCliente);
        this.usoTotal = usoTotal;
    }


    public Uso getUsoTotal() {
        return usoTotal;
    }

    public void setUsoTotal(Uso usoTotal) {
        this.usoTotal = usoTotal;
    }


    public static double getValorTotal(Cliente c) throws SQLException, ClassNotFoundException{
        
        
        double valorTotal = 0;
        int tempo = new UsoDAO().buscaTempo(c.getIdUsuario());
        double valorHora = new UsoDAO().buscaValorHora(c.getIdUsuario());
        Uso uso = new UsoDAO().buscarPorIdUsuario(c.getIdUsuario());
        double somaImpressoes = new ServicoDAO().buscarSomaImpressoes(c.getIdUsuario());
        
        
        if(c.getUsoTotal()!= null){
            for(EProduto p :uso.getServico().getListaProdutos()){
                valorTotal += p.getValor();
                
            }

            for (Impressoes i :uso.getServico().getListaImpressoes()) {
                valorTotal += i.getPreco();
            }
        }
       

        return valorTotal + somaImpressoes + (tempo*valorHora/60);

    }

}
