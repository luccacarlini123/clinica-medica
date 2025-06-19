package br.com.mouzetech.clinicamedica.domain.service.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public CidadeNaoEncontradoException(Long idCidade) {
		super("Não foi encontrada uma cidade com o id: " + idCidade);
	}
	
}
