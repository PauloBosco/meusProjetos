package br.com.lanhouse;

import br.com.lanhouse.model.e.EProduto;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.equipamento.Computador;
import br.com.lanhouse.model.pessoas.Admin;
import br.com.lanhouse.model.pessoas.Cliente;
import br.com.lanhouse.model.servicos.Impressoes;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.model.servicos.Uso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        Servico serv = new Servico(1,"55442");
        Computador pc1 = new Computador();
        Impressoes imp1 = new Impressoes(1,serv,"Documento1",2);
//        Uso uso1 = new Uso(1,1,pc1, serv, 3, LocalDate.now(),60);
        Cliente cliente = new Cliente(1,"Zezinho","Ze01","senha123",ETipoCliente.C);

        Admin adm = new Admin(1,"Paulo","PauloB","paulojfbosco@gmail.com", ETipoCliente.A);

        cliente.getUsoTotal().getServico().addProdutos(EProduto.SALGADO);
        cliente.getUsoTotal().getServico().addProdutos(EProduto.COCA);
        cliente.getUsoTotal().getServico().addImpressoes(imp1);

        cliente.getUsoTotal().setQtidadeMinutosTempo(60);

        for (EProduto p :cliente.getUsoTotal().getServico().getListaProdutos()) {
            System.out.println();
            System.out.println("Nome Produto: "+ p.getNome());
            System.out.println("Valor Produto: "+ p.getValor());
        }

        for(Impressoes i :cliente.getUsoTotal().getServico().getListaImpressoes()){
            System.out.println();
            System.out.println("Nome Impressao: "+ i.getNome());
            System.out.println("Valor Produto: "+ i.getPreco());
        }

        System.out.println();
        System.out.println("###### Total Valores ######");
        //System.out.println("Valor Total: R$" + Cliente.getValorTotal(cliente));





        System.out.println(cliente.getUsoTotal().getDiaLogin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        System.out.println(dataHoraAtual.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));





    }

}