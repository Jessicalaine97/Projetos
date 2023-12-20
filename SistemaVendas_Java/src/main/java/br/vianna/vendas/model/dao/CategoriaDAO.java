package br.vianna.vendas.model.dao;

import br.vianna.vendas.model.Categoria;
import br.vianna.vendas.model.faces.IGenericsDAO;
import br.vianna.vendas.model.vendas.Cliente;
import br.vianna.vendas.util.conection.ConectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CategoriaDAO implements IGenericsDAO<Categoria> {
    public void inserir(Categoria cat) throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c= ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql="INSERT INTO vendas.categoria " +
                "(nome, temIsencao) " +
                "VALUES(?,?);";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, cat.getNome());
        ps.setBoolean(2, cat.isTemIsencao());

        //4. Executar no BD
        ps.execute();
    }

    @Override
    public void alterar(Categoria cli) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void apagar(int key) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Categoria buscarPelaChave(int key) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Categoria> buscarTodos() throws SQLException, ClassNotFoundException {
        return null;
    }
}
