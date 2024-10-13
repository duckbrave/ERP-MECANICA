package modelo.dominio.dao.usuario;

import gestao.Usuario;
import view.imagens.modelo.LoginDto;

import javax.swing.JOptionPane;

import gestao.Perfil;
import modelo.exceptio.NegocioException;

public class AutenticacaoDao {

	private final UsuarioDAO usuarioDao;

	public AutenticacaoDao() {
		this.usuarioDao = new UsuarioDAO();
	}

	public boolean temPermissao(Usuario usuario) {
		try {
			permissao(usuario);
			return true;
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Usuário sem permissão", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private void permissao(Usuario usuario) {
		if (!Perfil.ADMIN.equals(usuario.getPerfil())) {
			throw new NegocioException("Sem permissão para realizar essa ação");
		}
	}

	public Usuario login(LoginDto login) {
		// Busca o usuário pelo nome de usuário fornecido
		Usuario usuario = usuarioDao.buscarUsuarioUser(login.getUsuario());

		// Verifica se o usuário não foi encontrado ou está desativado
		if (usuario == null || !usuario.isEstado()) {
			return null; // Retorna null se o usuário não existir ou estiver desativado
		}

		// Verifica se a senha está correta
		if (validaSenha(usuario.getSenha(), login.getSenha())) {
			return usuario; // Retorna o usuário se a autenticação for bem-sucedida
		}

		return null; // Retorna null se a senha estiver incorreta
	}

	// senha sem criptografia
	private boolean validaSenha(String senhaUsuario, String senhaLogin) {
		return senhaUsuario.equals(senhaLogin); // Compara as senhas
	}
}
