package modelo.dominio.dao.usuario;

import java.time.LocalDateTime;
import java.util.Scanner;

import gestao.Perfil;
import gestao.Usuario;

public class CadastroUsuario {
    private final UsuarioDAO usuarioDAO;
    private final Scanner scanner;

    public CadastroUsuario(Scanner scanner) {
        this.usuarioDAO = new UsuarioDAO();
        this.scanner = scanner;
    }

    public void coletarDadosUsuario() {
        try {
            Usuario usuario = new Usuario();

            System.out.print("Nome: ");
            usuario.setNome(scanner.nextLine().trim());

            System.out.print("Usuário: ");
            usuario.setUsername(scanner.nextLine().trim());

            if (usuario.getUsername().isEmpty()) {
                System.out.println("O nome de usuário não pode ser vazio.");
                return;
            }

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            System.out.print("Confirme a senha: ");
            String senhaConfirmacao = scanner.nextLine();

            if (!senha.equals(senhaConfirmacao)) {
                System.out.println("As senhas não coincidem. Cadastro cancelado.");
                return;
            }

            usuario.setSenha(senha);

            System.out.print("Perfil (ADMIN ou USER): ");
            String perfilInput = scanner.nextLine().toUpperCase();
            try {
                usuario.setPerfil(Perfil.valueOf(perfilInput));
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: Perfil inválido. Use ADMIN ou USER.");
                return;
            }

            System.out.print("Estado (ativo = true, inativo = false): ");
            boolean estado = scanner.nextBoolean();
            usuario.setEstado(estado);
            scanner.nextLine(); // Consumir a nova linha

            // Define a data de criação
            usuario.setDataHoraCriacao(LocalDateTime.now());

            // Salva o usuário
            String resultado = usuarioDAO.salvar(usuario);
            System.out.println(resultado);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: Perfil inválido. Use ADMIN ou USER.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
