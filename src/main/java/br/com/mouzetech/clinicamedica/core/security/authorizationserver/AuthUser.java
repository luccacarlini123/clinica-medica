package br.com.mouzetech.clinicamedica.core.security.authorizationserver;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.mouzetech.clinicamedica.domain.model.TipoUsuario;
import br.com.mouzetech.clinicamedica.domain.model.Usuario;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AuthUser extends User {

	private static final long serialVersionUID = -3656567298206066618L;

	@EqualsAndHashCode.Include
	private Long usuarioId;

	private String nome;

	private TipoUsuario tipoUsuario;

	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);

		this.nome = usuario.getNome();
		this.usuarioId = usuario.getId();
		this.tipoUsuario = usuario.getTipo();
	}

	public String getNome() {
		return nome;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
}