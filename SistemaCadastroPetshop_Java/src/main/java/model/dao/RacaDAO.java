package model.dao;

import model.Raca;
import util.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.e.ETipoAnimal;

public class RacaDao {
    private Connection connection;

    private static RacaDao instance;

    public static RacaDao getInstance() {
        if (instance == null) {
            instance = new RacaDao();
        }
        return instance;
    }

    public static void inserir(Raca raca) {
        String sql = "INSERT INTO raca (id, tipoAnimal, nome) VALUES (?, ?, ?)";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
              PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, raca.getId());
            stmt.setString(2, raca.getTipoAnimal().toString());
            stmt.setString(3, raca.getNome());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                raca.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(Raca raca) {
        String sql = "UPDATE raca SET tipoAnimal=?, nome=? WHERE id=?";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
             PreparedStatement stmt = connectionFactory.prepareStatement(sql)) {

            stmt.setString(1, raca.getTipoAnimal().toString());
            stmt.setString(2, raca.getNome());
            stmt.setInt(3, raca.getId());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void apagar(int id) {
        String sql = "DELETE FROM raca WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Raca buscarPelaChave(int id) {
        String sql = "SELECT * FROM raca WHERE id=?";
        Raca raca = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                raca = criarRaca(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return raca;
    }

    public static List<Raca> buscarTodos() {
        String sql = "SELECT * FROM raca";
        List<Raca> listaRacas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Raca raca = criarRaca(rs);
                listaRacas.add(raca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaRacas;
    }

    public static Raca criarRaca(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        ETipoAnimal tipoAnimal = ETipoAnimal.valueOf(rs.getString("tipoAnimal"));
        String nome = rs.getString("nome");
        return new Raca(id, tipoAnimal, nome);
    }
    
     public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM raca";
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
