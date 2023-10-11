package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.servicos.Impressoes;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImpressoesDAO implements IGenericDAO<Impressoes,Integer> {
    @Override
    public void inserir(Impressoes obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "INSERT INTO `bdprojetolanhouse`.`impressoes`\n" +
                    "(`id_servico`,`nomeDocumento`,`preco`)\n" +
                    "VALUES(?,?,?);\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdServico().getIdServico());
            pst.setString(2,obj.getNome());
            pst.setDouble(3,obj.getPreco());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Impressoes obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE `bdprojetolanhouse`.`impressoes`\n" +
                    "SET `id_servico` = ?,`nomeDocumento` = ?,`preco` = ? \n" +
                    "WHERE `id_impressoes` = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdServico().getIdServico());
            pst.setString(2,obj.getNome());
            pst.setDouble(3,obj.getPreco());
            pst.setInt(4,obj.getIdImpressoes());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Impressoes obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM `bdprojetolanhouse`.`impressoes`\n" +
                    "WHERE id_impressoes = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdImpressoes());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public Impressoes buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "select i.*, s.*\n" +
                    "from impressoes i\n" +
                    "inner join servico s on i.id_servico = s.id_servico\n" +
                    "where id_impressoes = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Impressoes impressao = null;
            if(resultado.next()){
                impressao = new Impressoes(resultado.getInt(1),
                                            new Servico(resultado.getInt("id_servico"),
                                                        resultado.getString("cod_servico")
                                            ),
                                            resultado.getString("nomeDocumento"),
                                            resultado.getDouble("preco")
                                            );
            }
            return impressao;

        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Impressoes> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT *\n" +
                    "FROM impressoes;";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            ArrayList<Impressoes> lista = new ArrayList<>();
            while (resultado.next()){
                Impressoes imp = new Impressoes(resultado.getInt(1),
                                                new Servico(resultado.getInt(1),
                                                        resultado.getString(2)),
                                                resultado.getString(3),
                                                resultado.getDouble(4)
                                                );
                lista.add(imp);
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
            String sql = "SELECT count(*) \n"+
                    "FROM bdprojetolanhouse.impressoes;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
    
    public int quantidadePorLogin(String login) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "select count(*)\n" +
                        "from impressoes i\n" +
                        "inner join servico s on i.id_servico = s.id_servico\n" +
                        "inner join uso u on s.id_servico = u.id_servico\n" +
                        "inner join usuario us on u.id_usuario = us.id_usuario\n" +
                        "where login like ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1 , "%"+login+"%");
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                return resultado.getInt(1);
            }
            return 0;
            
            
        }finally {
            c.close();
        }
    }
}
