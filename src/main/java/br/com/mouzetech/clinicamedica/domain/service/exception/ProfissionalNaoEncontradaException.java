package br.com.mouzetech.clinicamedica.domain.service.exception;

public class ProfissionalNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -8668145719372311355L;

	public ProfissionalNaoEncontradaException(Long idProfissional) {
		super("Não foi encontrado um profissional com o id: " + idProfissional);
	}

}