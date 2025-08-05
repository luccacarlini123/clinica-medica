package br.com.mouzetech.clinicamedica.domain.service.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7274523031880471913L;

	public PermissaoNaoEncontradaException(Long idPermissao) {
		super("Não foi encontrada uma permissão com o id: " + idPermissao);
	}
}