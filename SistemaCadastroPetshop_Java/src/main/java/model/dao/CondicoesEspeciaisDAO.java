package model.dao;

import model.CondicoesEspeciais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Raca;
import util.connection.ConnectionFactory;

public class CondicoesEspeciaisDao {
   private Connection connection;
    
    private static CondicoesEspeciaisDao instance;

    public static CondicoesEspeciaisDao getInstance() {
        if (instance == null) {
            instance = new CondicoesEspeciaisDao();
        }
        return instance;
    }
    public static void inserir(CondicoesEspeciais condicoesEspeciais) {
        String sql = "INSERT INTO condicoesespeciais (idRaca, alergias, problemasDeSaude) VALUES (?, ?, ?)";

         try (Connection connectionFactory = ConnectionFactory.getConnection();
              PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, condicoesEspeciais.getRaca().getId());
            stmt.setString(2, condicoesEspeciais.getAlergias());
            stmt.setString(3, condicoesEspeciais.getProblemasDeSaude());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(CondicoesEspeciais condicoesEspeciais) {
        String sql = "UPDATE condicoesespeciais SET idRaca=?, alergias=?, problemasDeSaude=? WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, condicoesEspeciais.getRaca().getId());
            stmt.setString(2, condicoesEspeciais.getAlergias());
            stmt.setString(3, condicoesEspeciais.getProblemasDeSaude());
            stmt.setInt(4, condicoesEspeciais.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagar(int id) {
        String sql = "DELETE FROM condicoesespeciais WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CondicoesEspeciais buscarPelaChave(int id) {
        String sql = "SELECT * FROM condicoesespeciais WHERE id=?";
        CondicoesEspeciais condicoesEspeciais = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    condicoesEspeciais = criarCondicoesEspeciais(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condicoesEspeciais;
    }

    public static List<CondicoesEspeciais> buscarTodos() {
        String sql = "SELECT * FROM condicoesespeciais";
        List<CondicoesEspeciais> listaCondicoesEspeciais = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CondicoesEspeciais condicoesEspeciais = criarCondicoesEspeciais(rs);
                listaCondicoesEspeciais.add(condicoesEspeciais);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCondicoesEspeciais;
    }

    private static CondicoesEspeciais criarCondicoesEspeciais(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idRaca = rs.getInt("idRaca");
        String alergias = rs.getString("alergias");
        String problemasDeSaude = rs.getString("problemasDeSaude");

        Raca raca = RacaDao.buscarPelaChave(idRaca);

        return new CondicoesEspeciais(id, raca, alergias, problemasDeSaude);
    }
    
        public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM condicoesespeciais";
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
