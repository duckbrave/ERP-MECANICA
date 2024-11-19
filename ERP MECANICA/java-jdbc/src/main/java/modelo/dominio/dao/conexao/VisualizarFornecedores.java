package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisualizarFornecedores {
	private Conexao conexao;

	public VisualizarFornecedores(Conexao conexao) {
		this.conexao = conexao;
	}

	public void listarFornecedores() {
		String sql = "SELECT * FROM fornecedor";
		try (Connection conn = conexao.obterConexao();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			// Verifica se há fornecedores cadastrados
			if (!rs.next()) {
				System.out.println("Nenhum fornecedor cadastrado.");
				return;
			}

			// Itera sobre o ResultSet
			do {
				long cnpj = rs.getLong("cnpj");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				String rua = rs.getString("rua"); // Certifique-se que os campos correspondam à tabela
				String bairro = rs.getString("bairro");
				String cidade = rs.getString("cidade");

				// Formatação da saída
				System.out.println("----------------------------");
				System.out.printf("CNPJ: %d%nNome: %s%nTelefone: %s%nEmail: %s%nEndereço: %s, %s, %s%n", cnpj, nome,
						telefone, email, rua, bairro, cidade);
				System.out.println("----------------------------");
			} while (rs.next());

		} catch (SQLException e) {
			System.err.println("Erro ao listar fornecedores: " + e.getMessage());
			// Considere usar um logger em vez de System.err
		}
	}
}
