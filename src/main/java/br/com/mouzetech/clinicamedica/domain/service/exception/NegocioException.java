package br.com.mouzetech.clinicamedica.domain.service.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 9016610267086399112L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
}