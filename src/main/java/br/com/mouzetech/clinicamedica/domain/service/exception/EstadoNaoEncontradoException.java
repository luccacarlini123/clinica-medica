package br.com.mouzetech.clinicamedica.domain.service.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -3728079710897192060L;

	public EstadoNaoEncontradoException(Long idEstado) {
		super("Não foi encontrado um estado com o id: " + idEstado);
	}
	
}
