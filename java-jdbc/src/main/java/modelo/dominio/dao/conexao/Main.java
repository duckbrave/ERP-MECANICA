package modelo.dominio.dao.conexao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Cria uma nova conexão ao banco de dados
        Conexao conexao = new ConexaoSQL();
        // Instancia os objetos de cadastro para cliente e fornecedor
        CadastroCliente cadastroCliente = new CadastroCliente(conexao);
        CadastroFornecedor cadastroFornecedor = new CadastroFornecedor(conexao);
        VisualizarClientes visualizarClientes = new VisualizarClientes(conexao);
        VisualizarFornecedores visualizarFornecedores = new VisualizarFornecedores(conexao); // Nova instância
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Exibe o menu principal
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar Cliente2");
                System.out.println("2. Cadastrar Fornecedor");
                System.out.println("3. Visualizar Clientes");
                System.out.println("4. Visualizar Fornecedores"); // Nova opção
                System.out.println("5. Sair");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        // Chama o método para coletar dados do cliente
                        cadastroCliente.coletarDadosCliente();
                        break;
                    case 2:
                        // Chama o método para coletar dados do fornecedor
                        cadastroFornecedor.coletarDadosFornecedor();
                        break;
                    case 3:
                        // Chama o método para visualizar os clientes
                        visualizarClientes.listarClientes();
                        break;
                    case 4:
                        // Chama o método para visualizar os fornecedores
                        visualizarFornecedores.listarFornecedores(); // Chamada para visualizar fornecedores
                        break;
                    case 5:
                        // Encerra o programa
                        System.out.println("Saindo...");
                        scanner.close();
                        return; // Encerra o programa
                    default:
                        // Caso a opção seja inválida
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine(); // Consumir a nova linha para evitar loop infinito
            }
        }
    }
}
