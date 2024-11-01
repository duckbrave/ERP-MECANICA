package modelo.dominio.dao.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gestao.Perfil;
import gestao.Usuario;
import modelo.dominio.dao.conexao.Conexao;
import modelo.dominio.dao.conexao.ConexaoSQL;

public class UsuarioDAO {

    private final Conexao conexao;

    public UsuarioDAO() {
        this.conexao = new ConexaoSQL(); // Instancia a conexão correta
    }

    public String salvar(Usuario usuario) {
        return usuario.getId() == 0L ? adicionar(usuario) : editar(usuario);
    }

    private String adicionar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado, data_criacao) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = conexao.obterConexao();
            conn.setAutoCommit(false); // Inicia a transação

            preparedStatement = conn.prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);

            preparedStatement.setObject(6, LocalDateTime.now()); // Define a data de criação

            int resultado = preparedStatement.executeUpdate();
            conn.commit(); // Confirma a transação

            return resultado == 1 ? "Usuário adicionado com sucesso" : "Não foi possível adicionar";
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Reverte a transação em caso de erro
                } catch (SQLException rollbackEx) {
                    System.out.println("Erro ao reverter a transação: " + rollbackEx.getMessage());
                }
            }
            return String.format("Erro: %s", e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                System.out.println("Erro ao fechar os recursos: " + closeEx.getMessage());
            }
        }
    }

    private String editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try (Connection conn = conexao.obterConexao();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preencherValoresPreparedStatement(preparedStatement, usuario);
            preparedStatement.setLong(6, usuario.getId());
            int resultado = preparedStatement.executeUpdate();

            return resultado == 1 ? "Usuário editado com sucesso" : "Não foi possível editar";
        } catch (SQLException e) {
            return String.format("Erro: %s", e.getMessage());
        }
    }

    private void preencherValoresPreparedStatement(PreparedStatement preparedStatement, Usuario usuario) throws SQLException {
        preparedStatement.setString(1, usuario.getNome());
        preparedStatement.setString(2, usuario.getUsername());
        preparedStatement.setString(3, usuario.getSenha());
        preparedStatement.setString(4, usuario.getPerfil().name());
        preparedStatement.setBoolean(5, usuario.isEstado());
    }

    public List<Usuario> buscarTodosUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = conexao.obterConexao();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                usuarios.add(getUsuario(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todos os usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario buscarUsuarioId(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = conexao.obterConexao();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null;
    }

    public Usuario buscarUsuarioUser(String usuario) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        try (Connection conn = conexao.obterConexao();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return getUsuario(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por nome de usuário: " + e.getMessage());
        }
        return null;
    }

    private Usuario getUsuario(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsername(result.getString("usuario"));
        usuario.setSenha(result.getString("senha"));
        usuario.setPerfil(Perfil.valueOf(result.getString("perfil")));
        usuario.setEstado(result.getBoolean("estado"));
        usuario.setDataHoraCriacao(result.getObject("data_criacao", LocalDateTime.class));
        return usuario;
    }
}
