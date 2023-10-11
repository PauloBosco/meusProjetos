package br.com.lanhouse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericDAO<C,K> {

    public void inserir(C obj) throws SQLException, ClassNotFoundException;
    public void alterar(C obj) throws SQLException, ClassNotFoundException;
    public void apagar(C obj) throws SQLException, ClassNotFoundException;
    public C buscar(K key) throws SQLException, ClassNotFoundException;
    public ArrayList<C> BuscarTodos() throws SQLException, ClassNotFoundException;
    public int quantidade() throws SQLException, ClassNotFoundException;

}
