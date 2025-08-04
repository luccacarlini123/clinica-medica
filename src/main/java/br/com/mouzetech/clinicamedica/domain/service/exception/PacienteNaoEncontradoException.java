package br.com.mouzetech.clinicamedica.domain.service.exception;

public class PacienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -6176438945493855917L;

	public PacienteNaoEncontradoException(Long idPaciente) {
		super("Não foi encontrado um paciente com o id: " + idPaciente);
	}
	
}
