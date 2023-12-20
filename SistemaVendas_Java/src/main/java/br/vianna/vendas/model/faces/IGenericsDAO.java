package br.vianna.vendas.model.faces;

import br.vianna.vendas.model.vendas.Cliente;
import br.vianna.vendas.util.conection.ConectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public interface IGenericsDAO<C> {
    public void inserir(C cli)
            throws SQLException, ClassNotFoundException;
    public void alterar(C cli)
            throws SQLException, ClassNotFoundException;
    public void apagar(int key)
            throws SQLException, ClassNotFoundException;
    public C buscarPelaChave(int cli)
            throws SQLException, ClassNotFoundException;
    public ArrayList<C> buscarTodos()
            throws SQLException, ClassNotFoundException;
}
