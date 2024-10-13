package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL implements Conexao {
    // Informações para se conectar ao banco
    private static final String URL = "jdbc:postgresql://localhost:5432/erp"; // URL do banco
    private static final String USER = "postgres"; // Usuário do banco
    private static final String PASSWORD = "1234"; // Senha do banco
    private static Connection connection = null;

    // Bloco estático para tentar conectar ao banco ao carregar a classe
    static {
        conectar();
    }

    // Construtor padrão
    public ConexaoSQL() {
        // Construtor vazio, pois o bloco estático já lida com a conexão
    }

    // Método privado para conectar ao banco de dados
    private static void conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                // Carregar o driver do PostgreSQL
                Class.forName("org.postgresql.Driver");
                // Estabelecer a conexão com o banco
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                // Desabilitar auto commit para controle manual de transações
                connection.setAutoCommit(false);
                System.out.println("Conectado ao banco de dados com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    @Override
    public Connection obterConexao() throws SQLException {
        // Verificar se a conexão está fechada ou nula, e reconectar se necessário
        if (connection == null || connection.isClosed()) {
            conectar();
        }
        return connection; // Retornar a conexão atual
    }
}
