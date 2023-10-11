package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.equipamento.Computador;
import br.com.lanhouse.model.pessoas.Admin;
import br.com.lanhouse.model.pessoas.Cliente;
import br.com.lanhouse.model.pessoas.Usuario;
import br.com.lanhouse.model.servicos.Servico;
import br.com.lanhouse.model.servicos.Uso;
import br.com.lanhouse.util.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClienteDAO implements IGenericDAO<Cliente,Integer> {
    @Override
    public void inserir(Cliente obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql="INSERT INTO bdprojetolanhouse.usuario " +
                    "(nome, login, senha, tipo) " +
                    "VALUES(?,?,?,'C');";

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
    public void alterar(Cliente obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {

            String sql = "UPDATE bdprojetolanhouse.usuario\n" +
                    "SET nome=?, login=?, senha=?, tipo = 'C'\n" +
                    "WHERE id_Usuario= ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getNome());
            pst.setString(2,obj.getLogin());
            pst.setString(3,obj.getSenha());
            pst.setInt(4,obj.getIdUsuario());

            pst.execute();

        }finally {
            c.close();
        }

    }

    @Override
    public void apagar(Cliente obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM bdprojetolanhouse.usuario\n" +
                    "WHERE login= ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,obj.getLogin());

            pst.execute();

        }finally {
            c.close();
        }

    }

    @Override
    public Cliente buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {


            String sql = "SELECT id_Usuario, nome, login, senha, tipo\n" +
                    "FROM usuario\n" +
                    "WHERE tipo = 'C' and id_Usuario = ?;\n";
            /*
            String sql = "select u.*, uso.*, c.*, s.*\n" +
                    "from usuario u\n" +
                    "inner join uso on u.id_usuario = uso.id_usuario\n" +
                    "inner join computador c on uso.id_computador = c.id_computador\n" +
                    "inner join servico s on s.id_servico = uso.id_servico\n" +
                    "where tipo = 'C' and u.id_usuario = ?;\n";
                */
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Cliente cliente = null;
            if(resultado.next()){
                cliente = new Cliente(resultado.getInt(1),
                        resultado.getString("nome"),
                        resultado.getString("login"),
                        resultado.getString("senha"),
                        ETipoCliente.valueOf(resultado.getString("tipo"))
                );
            /*
            if(resultado.next()){
                cliente = new Cliente(resultado.getInt(1),
                        resultado.getString("nome"),
                        resultado.getString("login"),
                        resultado.getString("senha"),
                        ETipoCliente.valueOf(resultado.getString("tipo")),
                                new Uso(resultado.getInt("id_uso"),
                                        resultado.getString("id_usuario"),
                                        resultado.getInt("id_computador"),
                                        resultado.getInt("id_servico"),
                                        resultado.getDouble("valorHora"),
                                        LocalDateTime.parse(resultado.getString("diaLogin"),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                        resultado.getInt("qtidadeMinutosTempo")
                                        ),
                                new Computador(resultado.getInt("id_computador"),
                                                resultado.getString("processador"),
                                                resultado.getInt("memoriaRam"),
                                                resultado.getInt("polegadasMonitor")
                                        ),
                                new Servico( resultado.getInt("id_servico"),
                                        resultado.getString("cod_servico")
                                ),
                                resultado.getDouble("valorHora"),
                                LocalDateTime.parse(resultado.getString("diaLogin"),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                resultado.getInt("qtidadeMinutosTempo")
                                )
                );


             */
            }
            return cliente;

        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Cliente> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT * \n" +
                    "FROM usuario\n" +
                    "WHERE tipo = 'C';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            return getRegistroToClientes(resultado);

        }finally {
            c.close();
        }
    }

    private ArrayList<Cliente> getRegistroToClientes(ResultSet resultado) throws SQLException {
        ArrayList<Cliente> lista  = new ArrayList<>();
        while (resultado.next()){
            Cliente cliente = new Cliente(resultado.getInt(1),
                                        resultado.getString(2),
                                        resultado.getString(3),
                                        resultado.getString(4),
                                        ETipoCliente.valueOf(resultado.getString(5)),
                                       null
            );
            lista.add(cliente);
        }
        return lista;
    }
    
    public Cliente verificaUsuario(String loginNome, String loginSenha) throws SQLException, ClassNotFoundException{
        
         Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT * \n" +
                    "FROM usuario\n" +
                    "WHERE login = ? and senha = ? and Tipo = 'C';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1,loginNome);
            pst.setString(2,loginSenha);
            ResultSet resultado = pst.executeQuery();
            
            if(resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setIdUsuario(resultado.getInt("id_Usuario"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setLogin(resultado.getString("login"));
                cliente.setSenha(resultado.getString("senha"));
                cliente.setTipoCliente(ETipoCliente.valueOf(resultado.getString("tipo")));
                return cliente;
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
                    "WHERE tipo = 'C';\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }

    public ArrayList<Cliente> buscaPorLogin(String login) throws SQLException, ClassNotFoundException {
        Connection c = ConnectionFactory.getConnectionMysql();
        String sql = "SELECT * FROM usuario WHERE login LIKE ?";
        PreparedStatement pst = c.prepareStatement(sql);

        pst.setString(1, "%"+login+"%");
        ResultSet resultSet = pst.executeQuery();
        ArrayList<Cliente> clientes = new ArrayList<>();

        while(resultSet.next()){
            Cliente cliente = new Cliente();
            cliente.setIdUsuario(resultSet.getInt("id_Usuario"));
            cliente.setNome(resultSet.getString("nome"));
            cliente.setLogin(resultSet.getString("login"));
            cliente.setSenha(resultSet.getString("senha"));
            cliente.setTipoCliente(ETipoCliente.valueOf(resultSet.getString("tipo")));
            clientes.add(cliente);
        }

        return clientes;

    }
}
