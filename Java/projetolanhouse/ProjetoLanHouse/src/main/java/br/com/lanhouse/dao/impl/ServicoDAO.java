package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.e.EProduto;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ServicoDAO implements IGenericDAO<Servico,Integer> {
    @Override
    public void inserir(Servico obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "INSERT INTO `bdprojetolanhouse`.`servico`\n" +
                    "(`cod_servico`)\n" +
                    "VALUES(?);";

            PreparedStatement pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,obj.getCod_servico());

            pst.execute();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            int idServico = 0;
            if (generatedKeys.next()) {
                idServico = generatedKeys.getInt(1);
            }

            String sql2="INSERT INTO `bdprojetolanhouse`.`produto`\n" +
                    "(`id_servico`,`tipoProduto`)\n" +
                    "VALUES(?,?);\n";

            PreparedStatement produtopst = c.prepareStatement(sql2);
            for (EProduto produto: obj.getListaProdutos()){
                produtopst.setInt(1,idServico);
                produtopst.setString(2,produto.getNome());

                produtopst.execute();
            }



        } finally {
            c.close();
        }
    }
    
    public void inserirProduto(Servico obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
           
            String sql2="INSERT INTO `bdprojetolanhouse`.`produto`\n" +
                    "(`id_servico`,`tipoProduto`)\n" +
                    "VALUES(?,?);\n";

            PreparedStatement produtopst = c.prepareStatement(sql2);
            for (EProduto produto: obj.getListaProdutos()){
                produtopst.setInt(1,obj.getIdServico());
                produtopst.setString(2,produto.getNome());

                produtopst.execute();
            }



        } finally {
            c.close();
        }
    }
    

    @Override
    public void alterar(Servico obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE `bdprojetolanhouse`.`servico`\n" +
                    "SET`cod_servico` = ?\n" +
                    "WHERE `id_servico` = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getCod_servico());
            pst.setInt(2,obj.getIdServico());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Servico obj) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM `bdprojetolanhouse`.`servico`\n" +
                    "WHERE id_servico = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdServico());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public Servico buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT `servico`.`id_servico`,`servico`.`cod_servico`\n" +
                    "FROM `bdprojetolanhouse`.`servico`\n" +
                    "WHERE id_servico = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);
            ResultSet resultado = pst.executeQuery();

            Servico servico = null;
            if(resultado.next()){
                servico = new Servico(resultado.getInt(1),
                                    resultado.getString(2)
                                    );
            }
            return  servico;

        }finally {
            c.close();
        }
    }
    
    public double buscarSomaImpressoes(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT sum(preco)\n" +
                        "FROM servico s\n" +
                        "inner join uso  u on s.id_servico = u.id_servico\n" +
                        "inner join impressoes i on s.id_servico = i.id_servico\n" +
                        "where id_usuario = ?;";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);
            ResultSet resultado = pst.executeQuery();

            Servico servico = null;
            if(resultado.next()){
                return  resultado.getDouble(1);
            }
           
            return 0;
        }finally {
            c.close();
        }
    }

    
    public double buscarSomaProdutos(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT sum(preco)\n" +
                        "FROM servico s\n" +
                        "inner join uso  u on s.id_servico = u.id_servico\n" +
                        "inner join impressoes i on s.id_servico = i.id_servico\n" +
                        "where id_usuario = ?;";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);
            ResultSet resultado = pst.executeQuery();

            Servico servico = null;
            if(resultado.next()){
                return  resultado.getDouble(1);
            }
           
            return 0;
        }finally {
            c.close();
        }
    }
    public Servico buscarPorCodigoServico(String codigo) throws SQLException, ClassNotFoundException {
    Connection c = ConnectionFactory.getConnectionMysql();
    try {
        String sql = "SELECT `servico`.`id_servico`, `servico`.`cod_servico` " +
                     "FROM `bdprojetolanhouse`.`servico` " +
                     "WHERE cod_servico = ?";

        PreparedStatement pst = c.prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet resultado = pst.executeQuery();

        Servico servico = null;
        if (resultado.next()) {
            servico = new Servico(resultado.getInt(1), resultado.getString(2));
        }
        return servico;

    } finally {
        c.close();
    }
}
    
    
    @Override
    public ArrayList<Servico> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT `servico`.`id_servico`,`servico`.`cod_servico`\n" +
                    "FROM `bdprojetolanhouse`.`servico`;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            ArrayList<Servico> lista = new ArrayList<>();
            while (resultado.next()){
                Servico servico = new Servico(resultado.getInt(1),
                                            resultado.getString(2)
                                            );
                lista.add(servico);
            }
            return lista;
        }finally {
            c.close();
        }
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM `bdprojetolanhouse`.`servico`;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
}
