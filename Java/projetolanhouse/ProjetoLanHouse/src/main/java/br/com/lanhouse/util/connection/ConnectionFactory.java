package br.com.lanhouse.util.connection;

import br.com.lanhouse.dao.impl.*;
import br.com.lanhouse.model.e.EProduto;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.equipamento.Computador;
import br.com.lanhouse.model.pessoas.Admin;
import br.com.lanhouse.model.pessoas.Cliente;
import br.com.lanhouse.model.pessoas.Usuario;
import br.com.lanhouse.model.servicos.Impressoes;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.model.servicos.Uso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConnectionFactory {

    public static Connection getConnectionMysql() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/bdprojetolanhouse";
        String login = "root"; //mudar login do BD
        String senha = "pcbosco1"; //mudar senha do BD

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,login,senha);
    }

    public static void main(String[] args) {
        try {
            Connection c = ConnectionFactory.getConnectionMysql();

            //Admin adm1 = new Admin(10,"PauloAdmin","adm321","444",ETipoCliente.A);
            Servico serv3 = new Servico(4,"99999");

            Servico serv = new Servico(2,"555442");
            Computador pc1 = new Computador(1,"i5",16,24);
            //Uso uso1 = new Uso(1,2,pc1, serv,3,LocalDate.now(),60);
            Cliente cliente01 = new Cliente(1,"Zezinho ZZZZ","PB01","senha123", ETipoCliente.C);


            Computador pc2 = new Computador(2,"i7",32,24);
            Servico serv2 = new Servico(3,"666666");
            //Uso uso2 = new Uso(2,2,pc1, serv2,6,LocalDate.now(),30);
            Cliente cliente02 = new Cliente(2,"Cliente Ficticio","Cliente02","senha321", ETipoCliente.C);


            Impressoes imp1 = new Impressoes(1,serv,"Documento1",2);
            Impressoes imp2 = new Impressoes(2,serv,"Documento2",3);
            serv.addImpressoes(imp1);
            serv.addImpressoes(imp2);
            serv.addProdutos(EProduto.COCA);
            serv.addProdutos(EProduto.SALGADO);

            EProduto.PEPSI.setServico(serv);
            EProduto.SALGADO.setServico(serv);

           serv3.addProdutos(EProduto.COCA);
            serv3.addProdutos(EProduto.SALGADO);

            serv2.addProdutos(EProduto.SALGADO);
            serv2.addProdutos(EProduto.SALGADO);
            serv2.addProdutos(EProduto.PEPSI);


           // new ClienteDAO().inserir(cliente05);
           //new ImpressoesDAO().inserir(imp1);
           //new ProdutoDAO().inserir(EProduto.PEPSI);
            //new UsoDAO().inserir(uso1);
            //new ServicoDAO().inserir(serv);
            //new AdminDAO().buscar(2);

            //new UsoDAO().buscar(3).getComputador()


            //System.out.println(new ClienteDAO().BuscarTodos());

//            ClienteDAO clienteDAO = new ClienteDAO();
//            for(Cliente cliente :clienteDAO.BuscarTodos()){
//                System.out.println(cliente.getNome());
//            }
//            ImpressoesDAO impressoesDAO = new ImpressoesDAO();
//            for (Impressoes imp: impressoesDAO.BuscarTodos()){
//                System.out.println(imp.getIdImpressoes());
//            }
//
//            UsoDAO usoDAO = new UsoDAO();
//            for (Uso uso : usoDAO.BuscarTodos()){
//                System.out.println(uso.getServico().getIdServico());
//            }
            //new ServicoDAO().inserir(serv3);


            System.out.println("Conectou ao banco");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado!!!");
        } catch (SQLException e) {
            System.out.println("Nao conectou ao banco " + e.getMessage());
        }


    }
}
