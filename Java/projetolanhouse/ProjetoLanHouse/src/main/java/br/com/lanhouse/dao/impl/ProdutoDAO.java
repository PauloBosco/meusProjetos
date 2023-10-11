package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.e.EProduto;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO implements IGenericDAO<EProduto,Integer> {
    @Override
    public void inserir(EProduto obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="INSERT INTO `bdprojetolanhouse`.`produto`\n" +
                    "(`id_servico`,`tipoProduto`)\n" +
                    "VALUES(?,?);";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getServico().getIdServico());
            pst.setString(2,obj.getNome().toString());

            pst.execute();

        }finally {
            c.close();
        }

    }

    @Override
    public void alterar(EProduto obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="UPDATE `bdprojetolanhouse`.`produto`\n" +
                    "SET `tipoProduto` = ?\n" +
                    "WHERE `id_produto` = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setInt(2,obj.getIdProduto());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(EProduto obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="DELETE FROM `bdprojetolanhouse`.`produto`\n" +
                    "WHERE id_produto = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdProduto());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public EProduto buscar(Integer key) throws SQLException, ClassNotFoundException { //Pela Classe EPRODUTO ser um ENUM não é possivel instanciar

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="SELECT `produto`.`id_produto`,`produto`.`id_servico`,`produto`.`tipoProduto`\n" +
                    "FROM `bdprojetolanhouse`.`produto`\n" +
                    "WHERE id_produto = ?";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);
            ResultSet resultado = pst.executeQuery();




        }finally {
            c.close();
        }
        return null;
    }

    @Override
    public ArrayList<EProduto> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="";

        }finally {
            c.close();
        }
        return null;
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n"+
                    "FROM bdprojetolanhouse.produto;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
}
