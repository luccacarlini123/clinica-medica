package br.com.mouzetech.clinicamedica.domain.service.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public UsuarioNaoEncontradoException(Long idUsuario) {
		super("Não foi encontrado um usuário com o id: " + idUsuario);
	}
	
}
