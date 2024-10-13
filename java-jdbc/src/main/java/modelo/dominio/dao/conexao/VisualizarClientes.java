package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizarClientes {
    private Conexao conexao;

    public VisualizarClientes(Conexao conexao) {
        this.conexao = conexao;
    }

    public void listarClientes() {
        String sql = "SELECT * FROM cliente";
        try (Connection conn = conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long cnpj = rs.getLong("cnpj");
                String telefone = rs.getString("telefone"); // Use getString para evitar problemas
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                System.out.println("----------------------------");
                System.out.println("CPF/cnpj: " + cnpj + ", Telefone: " + telefone + ", Nome: " + nome + ", Email: " + email);
                System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
