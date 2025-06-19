package br.com.mouzetech.clinicamedica.domain.service.exception;

public class EspecialidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1521584749555108031L;

	public EspecialidadeNaoEncontradaException(Long idEspecialidade) {
		super("Não foi encontrada uma especialidade com o id: " + idEspecialidade);
	}
	
}