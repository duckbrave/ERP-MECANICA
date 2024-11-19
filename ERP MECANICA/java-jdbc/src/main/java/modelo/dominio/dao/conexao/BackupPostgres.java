package modelo.dominio.dao.conexao;

import java.io.IOException;

public class BackupPostgres {

    private static final String PG_DUMP_PATH = "C:\\Program Files\\PostgreSQL\\17\\bin\\pg_dump.exe"; // Caminho para o pg_dump (ajuste conforme necessário)
    private static final String DB_HOST = "localhost"; // Endereço do host do banco de dados
    private static final String DB_PORT = "5432"; // Porta do banco de dados
    private static final String DB_NAME = "erp"; // Nome do banco de dados
    private static final String DB_USER = "postgres"; // Usuário do banco de dados
    private static final String DB_PASSWORD = "'1234"; // Senha do banco de dados
    private static final String BACKUP_DIR = "C:\\backup\\"; // Diretório onde o backup será salvo

    public static void realizarBackup() {
        // Monta o comando para executar o pg_dump
        String comando = String.format("\"%s\" -h %s -p %s -U %s -F c -b -v -f \"%s%s.backup\" %s", 
                                       PG_DUMP_PATH, 
                                       DB_HOST, 
                                       DB_PORT, 
                                       DB_USER, 
                                       BACKUP_DIR, 
                                       DB_NAME, 
                                       DB_NAME);

        try {
            // Executa o comando do sistema
            Process process = Runtime.getRuntime().exec(comando);

            // Definir a variável de ambiente para a senha (não recomendado para produção)
            process.environment().put("PGPASSWORD", DB_PASSWORD);

            // Espera o processo terminar
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup realizado com sucesso!");
            } else {
                System.out.println("Erro ao realizar o backup. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao executar o backup: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        realizarBackup();
    }
}
