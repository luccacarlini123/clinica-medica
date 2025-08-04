package br.com.mouzetech.clinicamedica.domain.service.exception;

public class ConsultaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 7984441604218058268L;

	public ConsultaNaoEncontradaException(Long idConsulta) {
		super("Não foi encontrada uma consulta com o id: " + idConsulta);
	}
}