package modelo.dominio.dao.conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import gestao.Cliente;
import gestao.Fornecedor;

public class CadastroCliente {
	private Conexao conexao;
	private Scanner scanner;

	public CadastroCliente(Conexao conexao) {
		this.conexao = conexao;
		this.scanner = new Scanner(System.in);
	}

	public void coletarDadosCliente() {
		while (true) {
			System.out.println("Você está cadastrando um:");
			System.out.println("1. Cliente (CPF)");
			System.out.println("2. cliente (CNPJ)");
			System.out.println("3. Voltar ao menu principal");
			System.out.print("Escolha uma opção (1, 2 ou 3): ");
			int tipo = scanner.nextInt();
			scanner.nextLine(); // Consumir a nova linha

			switch (tipo) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				cadastrarFornecedor();
				break;
			case 3:
				System.out.println("Voltando ao menu principal...");
				return; // Retorna ao menu principal
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private void cadastrarCliente() {
		try {
			System.out.print("Digite o CPF: ");
			long cpf = Long.parseLong(scanner.nextLine().replaceAll("[^0-9]", ""));

			System.out.print("Digite o Telefone: ");
			long telefone = Long.parseLong(scanner.nextLine().replaceAll("[^0-9]", ""));

			System.out.print("Digite o Nome: ");
			String nome = scanner.nextLine();
			System.out.print("Digite o Email: ");
			String email = scanner.nextLine();
			System.out.print("Digite a Rua: ");
			String rua = scanner.nextLine();
			System.out.print("Digite o Bairro: ");
			String bairro = scanner.nextLine();
			System.out.print("Digite a Cidade: ");
			String cidade = scanner.nextLine();

			Cliente cliente = new Cliente(cpf, telefone, nome, email, rua, bairro, cidade);
			inserirCliente(cliente);
		} catch (NumberFormatException e) {
			System.out.println("Erro: CPF ou telefone inválido.");
		}
	}

	private void cadastrarFornecedor() {
		try {
			System.out.print("Digite o CNPJ: ");
			long cnpj = Long.parseLong(scanner.nextLine().replaceAll("[^0-9]", ""));

			System.out.print("Digite o Nome do Fornecedor: ");
			String nomeFornecedor = scanner.nextLine();
			System.out.print("Digite a Razão Social: ");
			String razaoSocial = scanner.nextLine();
			System.out.print("Digite a Inscrição Estadual: ");
			String inscricaoEstadual = scanner.nextLine();

			System.out.print("Digite o Telefone: ");
			long telefone = Long.parseLong(scanner.nextLine().replaceAll("[^0-9]", ""));
			System.out.print("Digite o Email: ");
			String email = scanner.nextLine();
			System.out.print("Digite a Rua: ");
			String rua = scanner.nextLine();
			System.out.print("Digite o Bairro: ");
			String bairro = scanner.nextLine();
			System.out.print("Digite a Cidade: ");
			String cidade = scanner.nextLine();
			System.out.print("Digite o Representante: ");
			String representante = scanner.nextLine();

			Fornecedor fornecedor = new Fornecedor(cnpj, razaoSocial, inscricaoEstadual, representante, telefone, email,
					rua, bairro, cidade, nomeFornecedor);
			inserirClienteCNPJ(fornecedor);
		} catch (NumberFormatException e) {
			System.out.println("Erro: CNPJ ou telefone inválido.");
		}
	}

	public void inserirCliente(Cliente cliente) {
		String inserirSQL = "INSERT INTO cliente (cnpj, telefone, nome, email, rua, bairro, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null; // Declara a conexão fora do try
		try {
			conn = conexao.obterConexao();
			conn.setAutoCommit(false); // Desabilitar auto-commit
			try (PreparedStatement stmt = conn.prepareStatement(inserirSQL)) {
				// Configurar os parâmetros do PreparedStatement
				stmt.setLong(1, cliente.getCnpj()); // Usa o CNPJ (ou CPF, pois ambos estão na mesma coluna)
				stmt.setLong(2, cliente.getTelefone());
				stmt.setString(3, cliente.getNome());
				stmt.setString(4, cliente.getEmail());
				stmt.setString(5, cliente.getRua());
				stmt.setString(6, cliente.getBairro());
				stmt.setString(7, cliente.getCidade());

				// Executa a inserção
				int rowsAffected = stmt.executeUpdate();
				conn.commit(); // Realiza o commit
				System.out.println("Cliente cadastrado com sucesso! Linhas inseridas: " + rowsAffected);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
			if (conn != null) { // Verifica se a conexão não é nula antes de tentar fazer o rollback
				try {
					conn.rollback(); // Desfaz as alterações em caso de erro
				} catch (SQLException rollbackEx) {
					System.out.println("Erro ao reverter a transação: " + rollbackEx.getMessage());
				}
			}
		} finally {
			if (conn != null) { // Fecha a conexão no bloco finally
				try {
					conn.close();
				} catch (SQLException closeEx) {
					System.out.println("Erro ao fechar a conexão: " + closeEx.getMessage());
				}
			}
		}
	}

	public void inserirClienteCNPJ(Fornecedor fornecedor) {
	    String inserirFornecedorSQL = "INSERT INTO cliente(cnpj, nome, razao_social, ie_cli, representante, telefone, email, rua, bairro, cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    // ... restante do código


		Connection conn = null; // Declara a conexão fora do try
		try {
			conn = conexao.obterConexao(); // Obtém a conexão
			conn.setAutoCommit(false); // Desabilitar auto-commit

			try (PreparedStatement stmt = conn.prepareStatement(inserirFornecedorSQL)) {
				// Configurar os parâmetros do PreparedStatement para o fornecedor
				stmt.setLong(1, fornecedor.getCnpj());
				stmt.setString(2, fornecedor.getNome()); // Agora estamos pegando o nome corretamente
				stmt.setString(3, fornecedor.getRazaoSocial());
				stmt.setString(4, fornecedor.getInscricaoEstadual());
				stmt.setString(5, fornecedor.getRepresentante());
				stmt.setLong(6, fornecedor.getTelefone());
				stmt.setString(7, fornecedor.getEmail());
				stmt.setString(8, fornecedor.getRua());
				stmt.setString(9, fornecedor.getBairro());
				stmt.setString(10, fornecedor.getCidade());

				// Executa a inserção do fornecedor
				int rowsAffected = stmt.executeUpdate();
				conn.commit(); // Realiza o commit
				System.out.println("Fornecedor cadastrado com sucesso! Linhas inseridas: " + rowsAffected);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
			if (conn != null) {
				try {
					conn.rollback(); // Desfaz as alterações em caso de erro
				} catch (SQLException rollbackEx) {
					System.out.println("Erro ao reverter a transação: " + rollbackEx.getMessage());
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close(); // Fecha a conexão
				} catch (SQLException closeEx) {
					System.out.println("Erro ao fechar a conexão: " + closeEx.getMessage());
				}
			}
		}
	}
}
