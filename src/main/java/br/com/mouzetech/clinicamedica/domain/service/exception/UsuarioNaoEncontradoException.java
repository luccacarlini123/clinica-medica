package br.com.mouzetech.clinicamedica.domain.service.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5272554181826760587L;

	public UsuarioNaoEncontradoException(Long idUsuario) {
		super("Não foi encontrado um usuário com o id: " + idUsuario);
	}
	
}
