package br.com.mouzetech.clinicamedica.domain.service.exception;

public class ConsultaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public ConsultaNaoEncontradaException(Long idConsulta) {
		super("Não foi encontrada uma consulta com o id: " + idConsulta);
	}
}