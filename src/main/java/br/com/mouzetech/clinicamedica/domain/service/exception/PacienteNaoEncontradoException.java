package br.com.mouzetech.clinicamedica.domain.service.exception;

public class PacienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public PacienteNaoEncontradoException(Long idPaciente) {
		super("Não foi encontrado um paciente com o id: " + idPaciente);
	}
	
}
