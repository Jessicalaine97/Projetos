package model.dao;

import model.Animal;
import model.Raca;
import model.e.ETipoSexo;
import util.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao {
    private Connection connection;

    private static AnimalDao instance;

    public static AnimalDao getInstance() {
        if (instance == null) {
            instance = new AnimalDao();
        }
        return instance;
    }

    public static void inserir(Animal animal) {
        String sql = "INSERT INTO animal (nome, sexo, idade, peso, idRaca, cpfDono) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
             PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getSexo().toString());
            stmt.setInt(3, animal.getIdade());
            stmt.setInt(4, animal.getPeso());
            stmt.setInt(5, animal.getRaca().getId());
            stmt.setString(6, animal.getCpfDono());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(Animal animal) {
        String sql = "UPDATE animal SET nome=?, sexo=?, idade=?, peso=?, idRaca=?, cpfDono=? WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, String.valueOf(animal.getSexo()) );
            stmt.setInt(3, animal.getIdade());
            stmt.setInt(4, animal.getPeso());
            stmt.setInt(5, animal.getRaca().getId());
            stmt.setString(6, animal.getCpfDono());
            stmt.setInt(7, animal.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagar(int id) {
        String sql = "DELETE FROM animal WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Animal buscarPelaChave(int id) {
        String sql = "SELECT * FROM animal WHERE id=?";
        Animal animal = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    animal = criarAnimal(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animal;
    }

    public static List<Animal> buscarTodos() {
        String sql = "SELECT * FROM animal";
        List<Animal> listaAnimais = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal animal = criarAnimal(rs);
                listaAnimais.add(animal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaAnimais;
    }

    private static Animal criarAnimal(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        ETipoSexo sexo = ETipoSexo.valueOf(rs.getString("sexo"));
        int idade = rs.getInt("idade");
        int peso = rs.getInt("peso");
        int idRaca = rs.getInt("idRaca");
        String cpfDono = rs.getString("cpfDono");

        Raca raca = RacaDao.buscarPelaChave(idRaca);

        return new Animal(id, nome, sexo, idade, peso, raca, cpfDono);
    }

    public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM animal";
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
