package br.com.mouzetech.clinicamedica.domain.service.exception;

public class ConsultaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public ConsultaNaoEncontradoException(Long idConsulta) {
		super("Não foi encontrada uma consulta com o id: " + idConsulta);
	}
}