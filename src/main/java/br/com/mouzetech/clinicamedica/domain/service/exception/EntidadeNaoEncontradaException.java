package br.com.mouzetech.clinicamedica.domain.service.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	
	private static final long serialVersionUID = -669439666206546979L;
	
	protected EntidadeNaoEncontradaException() {
		super("Entidade não encontrada");
	}
	
	protected EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}