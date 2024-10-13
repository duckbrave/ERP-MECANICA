package modelo.dominio.dao.usuario;

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
        String sql = "INSERT INTO usuario(nome, usuario, senha, perfil, estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);
            int resultado = preparedStatement.executeUpdate();
            return resultado == 1 ? "Usuário adicionado com sucesso" : "Não foi possível adicionar";
        } catch (SQLException e) {
            return String.format("Erro: %s", e.getMessage());
        }
    }

    private String editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, usuario = ?, senha = ?, perfil = ?, estado = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preencherValoresPreparedStatement(preparedStatement, usuario);
            preparedStatement.setLong(6, usuario.getId()); // Ajustar a posição do ID
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
        try {
            PreparedStatement statement = conexao.obterConexao().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usuarios.add(getUsuario(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(String.format("Erro: %s", e.getMessage()));
        }
        return usuarios;
    }
    
    public Usuario buscarUsuarioId(Long id) {
        String sql = String.format("SELECT * FROM usuario WHERE id = %d", id);
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUsuario(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Erro: %s", e.getMessage()));
        }
        return null; // Retorna null caso não encontre o usuário
    }
    
    public Usuario buscarUsuarioUser(String usuario) {
        String sql = "SELECT * FROM usuario WHERE usuario = ?";
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(sql);
            preparedStatement.setString(1, usuario); // Corrigido para utilizar o índice correto
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUsuario(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Erro: %s", e.getMessage()));
        }
        return null; // Retorna null caso não encontre o usuário
    }
    
    
    

    private Usuario getUsuario(ResultSet result) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(result.getLong("id"));
        usuario.setNome(result.getString("nome"));
        usuario.setUsername(result.getString("usuario")); // Ajuste no nome da coluna para "usuario"
        usuario.setSenha(result.getString("senha"));
        usuario.setPerfil(Perfil.valueOf(result.getString("perfil"))); // Converte para enum
        usuario.setEstado(result.getBoolean("estado"));
        usuario.setDataHoraCriacao(result.getObject("data_criacao", LocalDateTime.class)); // Alterado para LocalDateTime
        usuario.setUltimoLogin(result.getObject("ultimo_login", LocalDateTime.class)); // Alterado para LocalDateTime
        return usuario;
    }
}
