package br.com.lanhouse.dao.impl;

import br.com.lanhouse.dao.IGenericDAO;
import br.com.lanhouse.model.e.ETipoCliente;
import br.com.lanhouse.model.equipamento.Computador;
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

public class UsoDAO implements IGenericDAO<Uso,Integer> {
    @Override
    public void inserir(Uso obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "INSERT INTO `bdprojetolanhouse`.`uso`\n" +
                    "(`id_usuario`,`id_computador`,`id_servico`,`valorHora`,`diaLogin`,`qtidadeMinutosTempo`)\n" +
                    "VALUES(?,?,?,?,?,?);";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getIdUsuario().getIdUsuario());
            pst.setInt(2,obj.getComputador().getId());
            //pst.setInt(3,obj.getServico().getIdServico());
            if (obj.getServico() != null) {
            pst.setInt(3, obj.getServico().getIdServico());
            } else {
                pst.setNull(3, java.sql.Types.INTEGER);
            }
            pst.setDouble(4,obj.getValorHora());
            pst.setString(5,obj.getDiaLogin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            pst.setInt(6,obj.getQtidadeMinutosTempo());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public void alterar(Uso obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE `bdprojetolanhouse`.`uso`\n" +
                    "SET `id_usuario` = ?,`id_computador` = ?,`id_servico` = ?,`valorHora` = ?,`diaLogin` = ?,`qtidadeMinutosTempo` = ?\n" +
                    "WHERE `id_uso` = ?;\n";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getIdUsuario().getIdUsuario());
            pst.setInt(2,obj.getComputador().getId());
            pst.setInt(3,obj.getServico().getIdServico());
            pst.setDouble(4,obj.getValorHora());
            pst.setString(5,obj.getDiaLogin().toString());
            pst.setInt(6,obj.getQtidadeMinutosTempo());
            pst.setInt(7,obj.getIdUso());

            pst.execute();

        }finally {
            c.close();
        }

    }
    
    public void alterarIdServico(Uso obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "UPDATE `bdprojetolanhouse`.`uso`\n" +
                    "SET `id_servico` = ? \n" +
                    "WHERE `id_uso` = ?;\n";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, obj.getServico().getIdServico());
            pst.setInt(2,obj.getIdUso());

            pst.execute();

        }finally {
            c.close();
        }

    }

    @Override
    public void apagar(Uso obj) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "DELETE FROM `bdprojetolanhouse`.`uso`\n" +
                    "WHERE id_uso = ?;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,obj.getIdUso());

            pst.execute();

        }finally {
            c.close();
        }
    }

    @Override
    public Uso buscar(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select uso.*, u.*,c.*\n" +
                    "from uso \n" +
                    "inner join usuario u on uso.id_usuario = u.id_Usuario\n" +
                    "inner join computador c on uso.id_computador = c.id_computador\n" +
                    "where id_uso = ?;";



            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
                uso = new Uso(resultado.getInt(1),
                           new Cliente (resultado.getInt("id_Usuario"),
                                   resultado.getString("nome"),
                                   resultado.getString("login"),
                                   resultado.getString("senha"),
                                   ETipoCliente.valueOf(resultado.getString("tipo"))),
                        new Computador(resultado.getInt("id_computador"),
                                resultado.getString("processador"),
                                resultado.getInt("memoriaRam"),
                                resultado.getInt("polegadasMonitor")
                        ),
                        resultado.getDouble("valorHora"),
                        resultado.getInt("qtidadeMinutosTempo")
                        );
            }
            return uso;

        }finally {
            c.close();
        }
    }
    
    public Uso buscarPorIdUsuario(Integer key) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select uso.*, u.*,c.*\n" +
                    "from uso \n" +
                    "inner join usuario u on uso.id_usuario = u.id_Usuario\n" +
                    "inner join computador c on uso.id_computador = c.id_computador\n" +
                    "where uso.id_usuario = ?;";



            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,key);

            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
                uso = new Uso(resultado.getInt(1),
                           new Cliente (resultado.getInt("id_Usuario"),
                                   resultado.getString("nome"),
                                   resultado.getString("login"),
                                   resultado.getString("senha"),
                                   ETipoCliente.valueOf(resultado.getString("tipo"))),
                        new Computador(resultado.getInt("id_computador"),
                                resultado.getString("processador"),
                                resultado.getInt("memoriaRam"),
                                resultado.getInt("polegadasMonitor")
                        ),
                        resultado.getDouble("valorHora"),
                        resultado.getInt("qtidadeMinutosTempo")
                        );
            }
            return uso;

        }finally {
            c.close();
        }
    }
    
    public int buscaIdServico(Integer id_uso) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select uso.*, u.*,c.*\n" +
                    "from uso \n" +
                    "inner join usuario u on uso.id_usuario = u.id_Usuario\n" +
                    "inner join computador c on uso.id_computador = c.id_computador\n" +
                    "where id_uso = ?;";



            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1,id_uso);

            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
                
                return resultado.getInt("id_servico");
            }
            
            return 0;
            

        }finally {
            c.close();
        }
    }

    @Override
    public ArrayList<Uso> BuscarTodos() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try{
            String sql = "SELECT *" +
                    "FROM uso;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();

            ArrayList<Uso> lista = new ArrayList<>();
            while ((resultado.next())){

            }

            while (resultado.next()){
                Uso uso = new Uso(resultado.getInt(1),
                        new Cliente (resultado.getInt(2),
                                resultado.getString(3),
                                resultado.getString(4),
                                resultado.getString(5),
                                ETipoCliente.valueOf(resultado.getString(6))),
                        new Computador(resultado.getInt(7),
                                resultado.getString(8),
                                resultado.getInt(9),
                                resultado.getInt(10)
                        ),
                                resultado.getDouble(11),
                                resultado.getInt(12)
                                    );
                lista.add(uso);
            }


            return lista;

        }finally {
            c.close();
        }
    }

    public void adicionaTempo(int iduso, int tempo)throws SQLException, ClassNotFoundException {
        
         Connection c = ConnectionFactory.getConnectionMysql();
         try {
             
             String sql = "UPDATE `bdprojetolanhouse`.`uso`\n" +
                        "SET `qtidadeMinutosTempo` = qtidadeMinutosTempo + ? \n" +
                        "WHERE id_uso = ?;";


            PreparedStatement pst = c.prepareStatement(sql);
            
            pst.setInt(1,tempo);
            pst.setInt(2, iduso);
            
            
            
            pst.execute();
            
            
        } finally {
             c.close();
        }
    }
    public int buscaUltimo(int idUsuario) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select * from uso  where id_usuario = ? order by id_uso desc limit 1;";


            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
            
               return resultado.getInt(1);
                
            }
            //return uso;
            return 0;
        }finally {
            c.close();
        }
    };
    
    
    
    public int buscaTempo(int idUsuario) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select * from uso  where id_usuario = ? order by id_uso desc limit 1;";


            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
            
               return resultado.getInt(7);
                
            }
            //return uso;
            return 0;
        }finally {
            c.close();
        }
    };
    
    
    public int buscaValorHora(int idUsuario) throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();

        try {

            String sql = "select * from uso  where id_usuario = ? order by id_uso desc limit 1;";


            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            ResultSet resultado = pst.executeQuery();
            Uso uso = null;
            if (resultado.next()){
            
               return resultado.getInt(5);
                
            }
            //return uso;
            return 0;
        }finally {
            c.close();
        }
    };
    
    
    @Override
    public int quantidade() throws SQLException, ClassNotFoundException {

        Connection c = ConnectionFactory.getConnectionMysql();
        try {
            String sql = "SELECT count(*) \n" +
                    "FROM `bdprojetolanhouse`.`uso`;\n";

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            resultado.next();

            return resultado.getInt(1);

        }finally {
            c.close();
        }
    }
    
    
    public double somaServicosValorTotal(int idUsuario) throws SQLException, ClassNotFoundException{

        Connection c = ConnectionFactory.getConnectionMysql();
            try {
                String sql = "SELECT SUM(valorHora) AS soma_valores\n" +
                            "FROM uso\n" +
                            "WHERE id_usuario = ?;";

                PreparedStatement pst = c.prepareStatement(sql);
                pst.setInt(1, idUsuario);
                ResultSet resultado = pst.executeQuery();
                resultado.next();

                return resultado.getDouble(1);

            }finally {
                c.close();
            }
    }

    
}


