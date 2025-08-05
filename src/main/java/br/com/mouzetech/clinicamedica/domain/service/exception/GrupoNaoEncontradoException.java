package br.com.mouzetech.clinicamedica.domain.service.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -7274523031880471913L;

	public GrupoNaoEncontradoException(Long idGrupo) {
		super("Não foi encontrado um grupo com o id: " + idGrupo);
	}
}