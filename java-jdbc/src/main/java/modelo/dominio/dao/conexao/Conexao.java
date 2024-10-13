package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Conexao {
    // Método para obter a conexão com o banco de dados
    Connection obterConexao() throws SQLException;
}
