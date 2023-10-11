package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.pessoas.Admin;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAO implements IGenericDAO<Admin,Integer> {


    @Override
    public void inserir(Admin obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="INSERT INTO bdprojetolanhouse.usuario " +
                    "(nome, login, senha, tipo) " +
                    "VALUES(?,?,?,'A');";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getNome());
            pst.setString(2,obj.getLogin());
            pst.setString(3,obj.getSenha());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Admin obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "UPDATE bdprojetolanhouse.usuario\n" +
                    "SET nome=?, login=?, senha=?, tipo = 'A'>\n" +
                    "WHERE id_Usuario=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getNome());
            pst.setString(2,obj.getLogin());
            pst.setString(3,obj.getSenha());
            pst.setInt(4,obj.getIdUsuario());

        }finally {
            c.close();
        }
    }

    @Override
    public void apagar(Admin obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM bdprojetolanhouse.usuario\n" +
                    "WHERE id_Usuario=?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdUsuario());

        }finally {
            c.close();
        }
    }

    @Override
    public Admin buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {
            String sql = "SELECT id_Usuario, nome, login, senha, tipo\n" +
                    "FROM usuario\n" +
                    "WHERE tipo = 'A' and id_Usuario = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Admin admin = null;
            if(resultado.next()){
                admin = new Admin(resultado.getInt(1),
                                    resultado.getString("nome"),
                                    resultado.getString("login"),
                                    resultado.getString("senha"),
                                    ETipoCliente.valueOf(resultado.getString("tipo"))
                                    );
            }
            return admin;

        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Admin> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT * \n" +
                    "FROM usuario\n" +
                    "WHERE tipo = 'A';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            return getRegistroToAdmins(resultado);

        }finally {
            c.close();
        }

    }

    private ArrayList<Admin> getRegistroToAdmins(ResultSet resultado) throws SQLException {
        ArrayList<Admin> lista  = new ArrayList<>();
        while (resultado.next()){
            Admin admin = new Admin(resultado.getInt(1),
                                    resultado.getString(2),
                                    resultado.getString(3),
                                    resultado.getString(4),
                                    ETipoCliente.valueOf(resultado.getString(5))
                                    );
            lista.add(admin);
        }
    return lista;
    }
    
    public Admin verificaUsuario(String loginNome, String loginSenha) throws SQLException, ClassNotFoundException{
        
          Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT * \n" +
                    "FROM usuario\n" +
                    "WHERE login = ? and senha = ? and Tipo = 'A';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,loginNome);
            pst.setString(2,loginSenha);
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                Admin admin = new Admin();
                admin.setIdUsuario(resultado.getInt("id_Usuario"));
                admin.setNome(resultado.getString("nome"));
                admin.setLogin(resultado.getString("login"));
                admin.setSenha(resultado.getString("senha"));
                admin.setTipoCliente(ETipoCliente.valueOf(resultado.getString("tipo")));
                
                return admin;
            }
            
            return null;
            
            //return getRegistroToClientes(resultado);
            

        }finally {
            c.close();
        }
        
    }

    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n"+
                    "FROM bdprojetolanhouse.usuario;\n"+
                    "WHERE tipo = 'A';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
}
