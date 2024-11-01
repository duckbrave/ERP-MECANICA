package modelo.dominio.dao.conexao;

import java.util.Scanner;
import gestao.estoque.CadastroProduto;
import gestao.venda.VendaNFE;
import gestao.venda.VisualizarVendaNFE;
import modelo.agendamento.Agendamento;
import modelo.agendamento.VisualizarAgendamentos;
import modelo.dominio.dao.usuario.CadastroUsuario;
import modelo.dominio.dao.usuario.ListarUsuario;
import modelo.dominio.dao.usuario.UsuarioDAO;
import gestao.Usuario;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new ConexaoSQL();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Scanner scanner = new Scanner(System.in);

        // Autenticação do usuário antes de exibir o menu principal
        System.out.println("=== Sistema de Autenticação ===");
        Usuario usuarioAutenticado = null;
        CadastroUsuario cadastroUsuario = new CadastroUsuario(scanner);

        // Permitir que novos usuários se cadastrem
        while (usuarioAutenticado == null) {
            System.out.print("Nome de usuário (ou digite 'cadastrar' para criar um novo usuário): ");
            String username = scanner.nextLine();
            if (username.equalsIgnoreCase("cadastrar")) {
                cadastroUsuario.coletarDadosUsuario();
                continue; // Volta para o início do loop para tentar autenticar novamente
            }

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            Usuario usuario = usuarioDAO.buscarUsuarioUser(username);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                usuarioAutenticado = usuario;
                System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNome() + "!");
            } else {
                System.out.println("Usuário ou senha incorretos. Tente novamente.");
            }
        }

        // Instancia dos objetos de cadastro e visualização após a autenticação
        CadastroCliente cadastroCliente = new CadastroCliente(conexao);
        CadastroFornecedor cadastroFornecedor = new CadastroFornecedor(conexao);
        CadastroProduto cadastroProduto = new CadastroProduto(conexao);

        VisualizarClientes visualizarClientes = new VisualizarClientes(conexao);
        VisualizarFornecedores visualizarFornecedores = new VisualizarFornecedores(conexao);
        VisualizarAgendamentos visualizarAgendamentos = new VisualizarAgendamentos(conexao);

        Agendamento agendamento = new Agendamento(conexao);
        VendaNFE vendaNFE = new VendaNFE(conexao, scanner);
        VisualizarVendaNFE visualizador = new VisualizarVendaNFE(conexao);
        
        ListarUsuario listarUsuario = new ListarUsuario(scanner);

        // Menu principal do sistema
        while (true) {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Cadastrar Cliente");
                System.out.println("2. Cadastrar Fornecedor");
                System.out.println("3. Visualizar Clientes");
                System.out.println("4. Visualizar Fornecedores");
                System.out.println("5. Cadastrar Produto");
                System.out.println("6. Sair");
                System.out.println("7. Cadastrar Agendamento");
                System.out.println("8. Visualizar Agendamentos");
                System.out.println("9. Realizar Venda NF-e");
                System.out.println("10. Mostrar Vendas");
                System.out.println("11. Listar Usuários");

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
                        agendamento.agendar(scanner);
                        break;
                    case 8:
                        visualizarAgendamentos.listarAgendamentos();
                        break;
                    case 9:
                        vendaNFE.realizarVenda();
                        break;
                    case 10:
                        visualizador.exibirVendas();
                        break;
                    case 11:
                        listarUsuario.listarTodosUsuarios();
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
