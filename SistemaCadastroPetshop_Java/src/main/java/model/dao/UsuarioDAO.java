package model.dao;

import model.e.ETipoSexo;
import model.e.ETipoUsuario;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.connection.ConnectionFactory;

public class UsuarioDao {
    private Connection connection;
    
    private static UsuarioDao instance;

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }
        return instance;
    }

    public static void inserir(Usuario usuario) {
         String sql = "INSERT INTO usuario (nome, email, senha, cpf, tipoUsuario, sexo) VALUES (?, ?, md5(?), ?, ?, ?)";

         try (Connection connectionFactory = ConnectionFactory.getConnection();
              PreparedStatement stmt = connectionFactory.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

             stmt.setString(1, usuario.getNome());
             stmt.setString(2, usuario.getEmail());
             stmt.setString(3, usuario.getSenha());
             stmt.setString(4, usuario.getCpf());
             stmt.setString(5, String.valueOf(usuario.getTipoUsuario()));
             stmt.setString(6, String.valueOf(usuario.getSexo()));

             stmt.executeUpdate();

             ResultSet generatedKeys = stmt.getGeneratedKeys();
             if (generatedKeys.next()) {
                 usuario.setId(generatedKeys.getInt(1));
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    public static void alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome=?, email=?, senha=?, cpf=?, tipoUsuario=?, sexo=? WHERE id=?";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
              PreparedStatement stmt = connectionFactory.prepareStatement(sql)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setInt(5, usuario.getTipoUsuario().ordinal() + 1); // Adiciona 1 ao ordinal para ajustar ao valor no banco
            stmt.setString(6, String.valueOf(usuario.getSexo()));
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void apagar(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (Connection connectionFactory = ConnectionFactory.getConnection();
             PreparedStatement stmt = connectionFactory.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPelaChave(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";
        Usuario usuario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = criarUsuario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public static List<Usuario> buscarTodos() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = criarUsuario(rs);
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    public static Usuario criarUsuario(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String senha = rs.getString("senha");
        String cpf = rs.getString("cpf");
        ETipoUsuario tipoUsuario = ETipoUsuario.valueOf(rs.getString("tipoUsuario"));
        ETipoSexo sexo = ETipoSexo.valueOf(rs.getString("sexo"));

        Usuario usuario = new Usuario(id, nome, email, senha, cpf, tipoUsuario, sexo);

        return usuario;
    }


    public Usuario login(String email, String senha) {
        String sql = "SELECT * FROM usuario WHERE email=? AND senha=?";
        Usuario usuario = null;
        System.out.println(sql);
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = criarUsuario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    
    public static int obterProximoCodigo() {
        String sql = "SELECT MAX(id) FROM usuario";
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
