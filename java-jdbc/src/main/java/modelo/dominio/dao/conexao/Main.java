package modelo.dominio.dao.conexao;

import java.util.Scanner;
import gestao.estoque.CadastroProduto;
import gestao.venda.VendaNFE;
import gestao.venda.VisualizarVendaNFE;
import modelo.agendamento.Agendamento;
import modelo.agendamento.VisualizarAgendamentos;

public class Main {
    public static void main(String[] args) {
        // Cria uma nova conexão ao banco de dados
        Conexao conexao = new ConexaoSQL();

        // Instancia os objetos de cadastro
        CadastroCliente cadastroCliente = new CadastroCliente(conexao);
        CadastroFornecedor cadastroFornecedor = new CadastroFornecedor(conexao);
        CadastroProduto cadastroProduto = new CadastroProduto(conexao);

        VisualizarClientes visualizarClientes = new VisualizarClientes(conexao);
        VisualizarFornecedores visualizarFornecedores = new VisualizarFornecedores(conexao);
        VisualizarAgendamentos visualizarAgendamentos = new VisualizarAgendamentos(conexao);

        Agendamento agendamento = new Agendamento(conexao); // Instancia de Agendamento
        Scanner scanner = new Scanner(System.in); // Scanner criado aqui

        VendaNFE vendaNFE = new VendaNFE(conexao, scanner); // Passando o Scanner para VendaNFE
        VisualizarVendaNFE visualizador = new VisualizarVendaNFE(conexao);
        
        
        while (true) {
            try {
                // Exibe o menu principal
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Cadastrar Fornecedor");
                System.out.println("3. Visualizar Clientes");
                System.out.println("4. Visualizar Fornecedores");
                System.out.println("5. Cadastrar Produto");
                System.out.println("6. Sair");
                System.out.println("7. Cadastrar Agendamento");
                System.out.println("8. Visualizar Agendamentos");
                System.out.println("9. Realizar Venda NF-e"); // Novo menu para venda NF-e
                System.out.println("10. Mostrar vendas");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha

                switch (opcao) {
                    case 1:
                        cadastroCliente.coletarDadosCliente();
                        break;
                    case 2:
                        cadastroFornecedor.coletarDadosFornecedor();
                        break;
                    case 3:
                        visualizarClientes.listarClientes();
                        break;
                    case 4:
                        visualizarFornecedores.listarFornecedores();
                        break;
                    case 5:
                        cadastroProduto.coletarDadosProduto();
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    case 7:
                        // Chama o método para realizar o agendamento
                        agendamento.agendar(scanner);
                        break;
                    case 8:
                        visualizarAgendamentos.listarAgendamentos();
                        break;
                    case 9:
                        // Novo case para realizar a venda NF-e
                        vendaNFE.realizarVenda();
                        break;
                        
                    case 10:
                        // Novo case para realizar a venda NF-e
                    	visualizador.exibirVendas();
                        break;
                        
                    default:
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
