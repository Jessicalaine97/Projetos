package model.dao;

import model.Tratamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Consulta;
import util.connection.ConnectionFactory;

public class TratamentoDao {
    private Connection connection;
    private static TratamentoDao instance;

    public static TratamentoDao getInstance() {
        if (instance == null) {
            instance = new TratamentoDao();
        }
        return instance;
    }

    public static void inserir(Tratamento tratamento) {
        String sql = "INSERT INTO tratamento (idConsulta, medicamentos, procedimentos) VALUES (?, ?, ?)";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
              PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, tratamento.getConsulta().getId());
            stmt.setString(2, tratamento.getMedicamentos());
            stmt.setString(3, tratamento.getProcedimentos());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tratamento.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void alterar(Tratamento tratamento) {
        String sql = "UPDATE tratamento SET idConsulta=?, medicamentos=?, procedimentos=? WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, tratamento.getConsulta().getId());
            stmt.setString(2, tratamento.getMedicamentos());
            stmt.setString(3, tratamento.getProcedimentos());
            stmt.setInt(4, tratamento.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagar(int id) {
        String sql = "DELETE FROM tratamento WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Tratamento buscarPelaChave(int id) {
        String sql = "SELECT * FROM tratamento WHERE id=?";
        Tratamento tratamento = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tratamento = criarTratamento(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tratamento;
    }

    public static List<Tratamento> buscarTodos() {
        String sql = "SELECT * FROM tratamento";
        List<Tratamento> listaTratamentos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tratamento tratamento = criarTratamento(rs);
                listaTratamentos.add(tratamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTratamentos;
    }

    public static Tratamento criarTratamento(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idConsulta = rs.getInt("idConsulta");
        String medicamentos = rs.getString("medicamentos");
        String procedimentos = rs.getString("procedimentos");

        Consulta consulta = ConsultaDao.buscarPelaChave(idConsulta);
        return new Tratamento(id, consulta, medicamentos, procedimentos);
    }

    public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM tratamento";
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
