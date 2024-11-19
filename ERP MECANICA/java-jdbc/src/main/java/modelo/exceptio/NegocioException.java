package modelo.exceptio;

public class NegocioException extends RuntimeException {
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
