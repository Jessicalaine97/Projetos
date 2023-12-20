package model.dao;

import model.Animal;
import model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.connection.ConnectionFactory;

public class ConsultaDao {
    private Connection connection;
    
    private static ConsultaDao instance;

    public static ConsultaDao getInstance() {
        if (instance == null) {
            instance = new ConsultaDao();
        }
        return instance;
    }

    public static void inserir(Consulta consulta) {
        String sql = "INSERT INTO consulta (data, exames, idAnimal) VALUES (?, ?, ?)";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
             PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, new java.sql.Date(consulta.getData().getTime()));
            stmt.setString(2, consulta.getExames());
            stmt.setInt(3, consulta.getAnimal().getId());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    consulta.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID da consulta após a inserção.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(Consulta consulta) {
        String sql = "UPDATE consulta SET data=?, exames=?, idAnimal=? WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDate(1, new java.sql.Date(consulta.getData().getTime()));
            stmt.setString(2, consulta.getExames());
            stmt.setInt(3, consulta.getAnimal().getId());
            stmt.setInt(4, consulta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagar(int id) {
        String sql = "DELETE FROM consulta WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Consulta buscarPelaChave(int id) {
        String sql = "SELECT * FROM consulta WHERE id=?";
        Consulta consulta = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consulta = criarConsulta(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consulta;
    }

    public static List<Consulta> buscarTodos() {
        String sql = "SELECT * FROM consulta";
        List<Consulta> listaConsultas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta consulta = criarConsulta(rs);
                listaConsultas.add(consulta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaConsultas;
    }

    public static Consulta criarConsulta(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Date data = rs.getDate("data");
        String exames = rs.getString("exames");
        int idAnimal = rs.getInt("idAnimal");

        Animal animal = AnimalDao.buscarPelaChave(idAnimal);

        return new Consulta(id, data, exames, animal);
    }
    
    public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM consulta";
        int proximoCodigo = 0;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                proximoCodigo = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proximoCodigo;
    }
}
