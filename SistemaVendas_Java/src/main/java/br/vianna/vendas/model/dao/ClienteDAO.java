package br.vianna.vendas.model.dao;

import br.vianna.vendas.model.Categoria;
import br.vianna.vendas.model.faces.IGenericsDAO;
import br.vianna.vendas.model.vendas.Cliente;
import br.vianna.vendas.util.conection.ConectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClienteDAO implements IGenericsDAO<Cliente> {
    public void inserir(Cliente cli) throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c = ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql = "INSERT INTO vendas.usuario " +
                "(nome, email, login, senha, dataNascimento, perfil) " +
                "VALUES(?, ?, ?, md5(?), ?, 'CLIENTE');";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, cli.getNome());
        ps.setString(2, cli.getEmail());
        ps.setString(3, cli.getLogin());
        ps.setString(4, cli.getSenha());
        ps.setString(5, cli.getDataNascimento().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));

        //4. Executar no BD
        ps.execute();
    }

    @Override
    public void alterar(Cliente cli) throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c = ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql = "UPDATE vendas.usuario " +
                "SET nome=?, email=?, login=?, " + " data_nascimento=? " +
                "WHERE id=?; ";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, cli.getNome());
        ps.setString(2, cli.getEmail());
        ps.setString(3, cli.getLogin());
        ps.setString(4, cli.getDataNascimento().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        ps.setInt(5, cli.getId());
        //4. Executar no BD
        ps.execute();
    }

    @Override
    public void apagar(int key) throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c = ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql = "DELETE FROM vendas.usuario " +
                " WHERE id=?; ";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, key);
        //4. Executar no BD
        ps.execute();
    }

    @Override
    public Cliente buscarPelaChave(int key) throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c = ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql = "SELECT ID, nome, email, login, senha, dataNascimento, perfil " +
                " FROM vendas.usuario " +
                " WHERE id=? and perfil='CLIENTE'; ";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, key);
        //4. Executar no BD
        ResultSet rs = ps.executeQuery();
        //5. Converter linhas e colunas em objetos
        Cliente cli = null;
        if (rs.next()) {
            cli = new Cliente(rs.getString(2),
                    rs.getString(3),
                    rs.getString("login"),
                    rs.getString("senha"),
                    LocalDate.parse(rs.getString("data_nascimento"), DateTimeFormatter.ofPattern("YYYY-MM-dd")), null);
            cli.setId(rs.getInt("id"));

        } else {
            cli = null;
        }
        return cli;
    }

    @Override
    public ArrayList<Cliente> buscarTodos() throws SQLException, ClassNotFoundException {
        //1. Conectar com o banco
        Connection c = ConectionFactory.getConnection();
        //2. Montar uma consuta
        String sql = "SELECT ID, nome, email, login, senha, dataNascimento, perfil " +
                " FROM vendas.usuario " +
                " WHERE perfil='CLIENTE'; ";
        //3. Trocar os parametros
        PreparedStatement ps = c.prepareStatement(sql);
        //4. Executar no BD
        ResultSet rs = ps.executeQuery();
        //5. Converter linhas e colunas em objetos
        ArrayList<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cli = new Cliente(rs.getString(2),
                    rs.getString(3),
                    rs.getString("login"),
                    rs.getString("senha"),
                    LocalDate.parse(rs.getString("data_nascimento"), DateTimeFormatter.ofPattern("YYYY-MM-dd")), null);
                    cli.setId(rs.getInt("id"));
            clientes.add(cli);
            }
        return clientes;
        }

    public ArrayList<Cliente> buscarPeloNome() throws SQLException, ClassNotFoundException{
        return null;
    }
    public ArrayList<Cliente> buscarPeloLoginESenha(String login, String senha) throws SQLException, ClassNotFoundException{
        return null;
    }
}
