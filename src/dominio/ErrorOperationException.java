package dominio;

public class ErrorOperationException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public ErrorOperationException(String mensagem) {
		 super(mensagem);
	}
}
