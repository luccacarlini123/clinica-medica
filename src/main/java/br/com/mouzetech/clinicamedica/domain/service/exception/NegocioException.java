package br.com.mouzetech.clinicamedica.domain.service.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -8489818037949200441L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
}