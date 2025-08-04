package br.com.mouzetech.clinicamedica.domain.service.exception;

public class ConvenioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7922621464864510188L;

	public ConvenioNaoEncontradoException(Long idEstado) {
		super("Não foi encontrado um convênio com o id: " + idEstado);
	}
	
}
