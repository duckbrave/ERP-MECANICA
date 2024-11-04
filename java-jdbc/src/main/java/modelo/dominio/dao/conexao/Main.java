package modelo.dominio.dao.conexao;

import java.util.Scanner;

import gestao.estoque.CadastroProduto;
import gestao.venda.VendaNFE;
import gestao.venda.VisualizarVendaNFE;
import modelo.agendamento.Agendamento;
import modelo.agendamento.VisualizarAgendamentos;
import modelo.dominio.dao.usuario.AutenticacaoUsuario;
import modelo.dominio.dao.usuario.ListarUsuario;
import modelo.dominio.dao.usuario.UsuarioDAO;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new ConexaoSQL();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);

        // Autenticação do usuário antes de exibir o menu principal
        System.out.println("=== Sistema de Autenticação ===");
        AutenticacaoUsuario autenticacaoUsuario = new AutenticacaoUsuario(usuarioDAO, scanner);
        
        autenticacaoUsuario.autenticar();

        // Continua com a execução do sistema após a autenticação bem-sucedida
        CadastroCliente cadastroCliente = new CadastroCliente(conexao);
        CadastroFornecedor cadastroFornecedor = new CadastroFornecedor(conexao);
        CadastroProduto cadastroProduto = new CadastroProduto(conexao);
        
        VisualizarClientes visualizarClientes = new VisualizarClientes(conexao);
        VisualizarFornecedores visualizarFornecedores = new VisualizarFornecedores(conexao);
        VisualizarAgendamentos visualizarAgendamentos = new VisualizarAgendamentos(conexao);

        Agendamento agendamento = new Agendamento(conexao);
        VendaNFE vendaNFE = new VendaNFE(conexao, scanner);
        VisualizarVendaNFE visualizadorVendaNFE = new VisualizarVendaNFE(conexao);
        ListarUsuario listarUsuario = new ListarUsuario(scanner);

        executarMenuPrincipal(scanner, cadastroCliente, cadastroFornecedor, cadastroProduto, 
                              visualizarClientes, visualizarFornecedores, visualizarAgendamentos,
                              agendamento, vendaNFE, visualizadorVendaNFE, listarUsuario);
        
        scanner.close();
    }

    private static void executarMenuPrincipal(Scanner scanner, CadastroCliente cadastroCliente, CadastroFornecedor cadastroFornecedor,
                                              CadastroProduto cadastroProduto, VisualizarClientes visualizarClientes,
                                              VisualizarFornecedores visualizarFornecedores, VisualizarAgendamentos visualizarAgendamentos,
                                              Agendamento agendamento, VendaNFE vendaNFE, VisualizarVendaNFE visualizadorVendaNFE,
                                              ListarUsuario listarUsuario) {
        // Loop do menu principal
        while (true) {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Cadastrar Fornecedor");
                System.out.println("3. Cadastrar Agendamento");
                System.out.println("4. Cadastrar Produto");
                System.out.println("5. Visualizar Clientes");
                System.out.println("6. Visualizar Fornecedores");
                System.out.println("7. Listar Usuários");
                System.out.println("8. Visualizar Agendamentos");
                System.out.println("9. Realizar Venda NF-e");
                System.out.println("10. Mostrar Vendas");
                System.out.println("11. Sair");
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
                        agendamento.agendar(scanner);
                        break;
                    case 4:
                        cadastroProduto.coletarDadosProduto();
                        break;
                    case 5:
                        visualizarClientes.listarClientes();
                        break;
                    case 6:
                        visualizarFornecedores.listarFornecedores();
                        break;
                    case 7:
                        listarUsuario.listarTodosUsuarios();
                        break;
                    case 8:
                        visualizarAgendamentos.listarAgendamentos();
                        break;
                    case 9:
                        vendaNFE.realizarVenda();
                        break;
                    case 10:
                        visualizadorVendaNFE.exibirVendas();
                        break;
                    
                    case 11:
                        System.out.println("Saindo...");
                        return;
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
