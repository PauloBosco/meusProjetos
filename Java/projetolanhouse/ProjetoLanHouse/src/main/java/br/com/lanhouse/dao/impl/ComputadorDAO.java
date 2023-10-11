package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.equipamento.Computador;
import br.com.lanhouse.model.pessoas.Cliente;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComputadorDAO implements IGenericDAO<Computador,Integer> {
    @Override
    public void inserir(Computador obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql ="INSERT INTO `bdprojetolanhouse`.`computador`\n" +
                    "(`id_computador`,`processador`,`memoriaRam`,`polegadasMonitor`)\n" +
                    "VALUES(?,?,?,?);\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getId());
            pst.setString(2,obj.getProcessador());
            pst.setInt(3,obj.getMemoriaRam());
            pst.setInt(4,obj.getPolegadasMonitor());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Computador obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE `bdprojetolanhouse`.`computador`\n" +
                    "SET `processador` = ?,`memoriaRam` = ?,`polegadasMonitor` = ?\n" +
                    "WHERE `id_computador` = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getProcessador());
            pst.setInt(2,obj.getMemoriaRam());
            pst.setInt(3,obj.getPolegadasMonitor());
            pst.setInt(4,obj.getId());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Computador obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="DELETE FROM `bdprojetolanhouse`.`computador`\n" +
                    "WHERE id_computador = ?;";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getId());

            pst.execute();
        }finally {
            c.close();
        }
    }

    @Override
    public Computador buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="SELECT `computador`.`id_computador`,`computador`.`processador`,`computador`.`memoriaRam`,`computador`.`polegadasMonitor`\n" +
                    "FROM `bdprojetolanhouse`.`computador`\n"+
                    "WHERE id_computador = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Computador computador = null;
            if(resultado.next()){
                computador = new Computador(resultado.getInt(1),
                                            resultado.getString(2),
                                            resultado.getInt(3),
                                            resultado.getInt(4)
                                            );
            }
            return computador;
        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Computador> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT `computador`.`id_computador`,`computador`.`processador`,`computador`.`memoriaRam`,`computador`.`polegadasMonitor`\n" +
                    "FROM `bdprojetolanhouse`.`computador`\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            ArrayList<Computador> lista  = new ArrayList<>();
            while (resultado.next()){
                Computador comp = new Computador(resultado.getInt(1),
                                                resultado.getString(2),
                                                resultado.getInt(3),
                                                resultado.getInt(4)
                                                );
                lista.add(comp);
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
                    "FROM bdprojetolanhouse.computador;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
}
