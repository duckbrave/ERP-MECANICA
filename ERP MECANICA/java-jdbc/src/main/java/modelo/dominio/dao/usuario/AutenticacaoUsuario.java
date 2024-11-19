package modelo.dominio.dao.usuario;

import java.util.Scanner;

import gestao.Usuario;

public class AutenticacaoUsuario {

    private UsuarioDAO usuarioDAO;
    private Scanner scanner;
    private String perfil;

    public AutenticacaoUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        this.usuarioDAO = usuarioDAO;
        this.scanner = scanner;
    }

    public boolean autenticar() {
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verificar autenticação do usuário
        Usuario usuario = usuarioDAO.buscarUsuarioUser(username);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            // Após autenticação bem-sucedida, verificar o perfil do usuário
            perfil = usuarioDAO.obterPerfil(username);
            return true; // Usuário autenticado com sucesso
        } else {
            System.out.println("Usuário ou senha inválidos.");
            return false;
        }
    }

    // Método para verificar se o usuário é administrador
    public boolean isAdmin() {
        return "ADMIN".equals(perfil); // Se o perfil for ADMIN, o usuário é administrador
    }
}
