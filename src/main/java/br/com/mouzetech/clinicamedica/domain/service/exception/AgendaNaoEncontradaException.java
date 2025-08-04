package br.com.mouzetech.clinicamedica.domain.service.exception;

public class AgendaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7274523031880471913L;

	public AgendaNaoEncontradaException(Long idConsulta) {
		super("Não foi encontrada uma agenda com o id: " + idConsulta);
	}
}