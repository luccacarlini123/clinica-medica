package br.com.mouzetech.clinicamedica.domain.service.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 124266508097542169L;

	public CidadeNaoEncontradoException(Long idCidade) {
		super("Não foi encontrada uma cidade com o id: " + idCidade);
	}
	
}
