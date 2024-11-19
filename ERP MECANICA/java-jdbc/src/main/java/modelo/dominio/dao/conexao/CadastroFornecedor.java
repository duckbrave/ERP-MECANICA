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

    private String solicitarEntrada(String mensagem) {
        System.out.print(mensagem + " (ou digite 'voltar' para retornar): ");
        String entrada = scanner.nextLine();
        if ("voltar".equalsIgnoreCase(entrada)) {
            System.out.println("Retornando ao menu principal...");
            return null;
        }
        return entrada;
    }

    public void coletarDadosFornecedor() {
        while (true) {
            try {
                // Coletar dados do fornecedor
                String cnpjInput = solicitarEntrada("Digite o CNPJ:");
                if (cnpjInput == null) return;
                cnpjInput = cnpjInput.replaceAll("[^0-9]", "");
                if (cnpjInput.length() != 14) {
                    System.out.println("CNPJ inválido! Deve ter 14 dígitos.");
                    continue;
                }
                long cnpj = Long.parseLong(cnpjInput);

                String razaoSocial = solicitarEntrada("Digite a Razão Social:");
                if (razaoSocial == null) return;

                String inscricaoEstadual = solicitarEntrada("Digite a Inscrição Estadual:");
                if (inscricaoEstadual == null) return;

                String representante = solicitarEntrada("Digite o Representante:");
                if (representante == null) return;

                String telefoneInput = solicitarEntrada("Digite o Telefone:");
                if (telefoneInput == null) return;
                telefoneInput = telefoneInput.replaceAll("[^0-9]", "");
                if (telefoneInput.length() < 10 || telefoneInput.length() > 11) {
                    System.out.println("Número de telefone inválido! Deve ter entre 10 e 11 dígitos.");
                    continue;
                }
                long telefone = Long.parseLong(telefoneInput);

                String email = solicitarEntrada("Digite o Email:");
                if (email == null) return;

                String rua = solicitarEntrada("Digite a Rua:");
                if (rua == null) return;

                String bairro = solicitarEntrada("Digite o Bairro:");
                if (bairro == null) return;

                String cidade = solicitarEntrada("Digite a Cidade:");
                if (cidade == null) return;

                String nome = solicitarEntrada("Digite o Nome do Fornecedor:");
                if (nome == null) return;

                // Criar fornecedor
                Fornecedor fornecedor = new Fornecedor(cnpj, razaoSocial, inscricaoEstadual, representante, telefone,
                        email, rua, bairro, cidade, nome);
                System.out.println("Você deseja: ");
                System.out.println("1. Cadastrar");
                System.out.println("2. Cancelar");
                System.out.print("Escolha uma opção (1 ou 2): ");
                int opcao = Integer.parseInt(scanner.nextLine());
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
        String inserirSQL = "INSERT INTO fornecedor (cnpj, razao_social, ie_for, representante, telefone, email, rua, bairro, cidade, nome) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(inserirSQL)) {
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

            int rowsAffected = stmt.executeUpdate();
            conn.commit();
            System.out.println("Linhas inseridas: " + rowsAffected);
        }
    }
}
