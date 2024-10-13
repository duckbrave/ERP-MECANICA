package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import gestao.Fornecedor;

public class CadastroFornecedor {
	private Conexao conexao;
	private Scanner scanner;

	public CadastroFornecedor(Conexao conexao) {
		this.conexao = conexao;
		this.scanner = new Scanner(System.in);
	}

	public void coletarDadosFornecedor() {
		while (true) {
			try {
				// Coletar dados do fornecedor
				System.out.print("Digite o CNPJ: ");
				String cnpjInput = scanner.nextLine().replaceAll("[^0-9]", ""); // Remover caracteres não numéricos
				if (cnpjInput.length() != 14) {
					System.out.println("CNPJ inválido! Deve ter 14 dígitos.");
					continue;
				}
				long cnpj = Long.parseLong(cnpjInput); // Converte para long

				System.out.print("Digite a Razão Social: ");
				String razaoSocial = scanner.nextLine();
				System.out.print("Digite a Inscrição Estadual: ");
				String inscricaoEstadual = scanner.nextLine();
				System.out.print("Digite o Representante: ");
				String representante = scanner.nextLine();
				System.out.print("Digite o Telefone: ");
				String telefoneInput = scanner.nextLine().replaceAll("[^0-9]", ""); // Remover caracteres não numéricos
				if (telefoneInput.length() < 10 || telefoneInput.length() > 11) {
					System.out.println("Número de telefone inválido! Deve ter entre 10 e 11 dígitos.");
					continue;
				}
				long telefone = Long.parseLong(telefoneInput); // Converte para long
				System.out.print("Digite o Email: ");
				String email = scanner.nextLine();
				System.out.print("Digite a Rua: ");
				String rua = scanner.nextLine();
				System.out.print("Digite o Bairro: ");
				String bairro = scanner.nextLine();
				System.out.print("Digite a Cidade: ");
				String cidade = scanner.nextLine();
				System.out.print("Digite o Nome do Fornecedor: ");
				String nome = scanner.nextLine();

				// Criar fornecedor
				Fornecedor fornecedor = new Fornecedor(cnpj, razaoSocial, inscricaoEstadual, representante, telefone,
						email, rua, bairro, cidade, nome);
				System.out.println("Você deseja: ");
				System.out.println("1. Cadastrar");
				System.out.println("2. Cancelar");
				System.out.print("Escolha uma opção (1 ou 2): ");
				int opcao = Integer.parseInt(scanner.nextLine()); // Use nextLine e parse para evitar problemas
				switch (opcao) {
				case 1:
					try (Connection conn = conexao.obterConexao()) {
						inserirFornecedor(fornecedor, conn);
						System.out.println("Fornecedor cadastrado com sucesso!");
					} catch (SQLException e) {
						System.out.println("Erro ao cadastrar o fornecedor: " + e.getMessage());
					}
					return;

				case 2:
					System.out.println("Cadastro cancelado.");
					return;

				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, tente novamente.");
			}
		}
	}

	public void inserirFornecedor(Fornecedor fornecedor, Connection conn) throws SQLException {
		// SQL de inserção
		String inserirSQL = "INSERT INTO fornecedor (cnpj, razao_social, ie_for, representante, telefone, email, rua, bairro, cidade, nome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(inserirSQL)) {
			// Configurar os parâmetros do PreparedStatement
			stmt.setLong(1, fornecedor.getCnpj());
			stmt.setString(2, fornecedor.getRazaoSocial());
			stmt.setString(3, fornecedor.getInscricaoEstadual());
			stmt.setString(4, fornecedor.getRepresentante());
			stmt.setLong(5, fornecedor.getTelefone());
			stmt.setString(6, fornecedor.getEmail());
			stmt.setString(7, fornecedor.getRua());
			stmt.setString(8, fornecedor.getBairro());
			stmt.setString(9, fornecedor.getCidade());
			stmt.setString(10, fornecedor.getNome());

			// Executa a inserção
			 int rowsAffected = stmt.executeUpdate();
             conn.commit(); // Realiza o commit
			System.out.println("Linhas inseridas: " + rowsAffected);
		}
	}
}
