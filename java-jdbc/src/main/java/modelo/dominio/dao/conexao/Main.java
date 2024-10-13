package modelo.dominio.dao.conexao;

import java.util.Scanner;

import gestao.estoque.CadastroProduto;


public class Main {
    public static void main(String[] args) {
        // Cria uma nova conexão ao banco de dados
        Conexao conexao = new ConexaoSQL();

        // Instancia os objetos de cadastro
        CadastroCliente cadastroCliente = new CadastroCliente(conexao);
        CadastroFornecedor cadastroFornecedor = new CadastroFornecedor(conexao);
        CadastroProduto cadastroProduto = new CadastroProduto(conexao); // Nova instância para cadastro de produto

        VisualizarClientes visualizarClientes = new VisualizarClientes(conexao);
        VisualizarFornecedores visualizarFornecedores = new VisualizarFornecedores(conexao);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Exibe o menu principal
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Cadastrar Fornecedor");
                System.out.println("3. Visualizar Clientes");
                System.out.println("4. Visualizar Fornecedores");
                System.out.println("5. Cadastrar Produto"); // Nova opção para cadastrar produto
                System.out.println("6. Sair");
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
                        visualizarFornecedores.listarFornecedores();
                        break;
                    case 5:
                        // Chama o método para coletar dados do produto
                        cadastroProduto.coletarDadosProduto();
                        break;
                    case 6:
                        // Encerra o programa
                        System.out.println("Saindo...");
                        scanner.close();
                        return; // Encerra o programa
                    default:
                        // Caso a opção seja inválida
       
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine(); // Consumir a nova linha para evitar loop infinito
            }
        }
    }
}
