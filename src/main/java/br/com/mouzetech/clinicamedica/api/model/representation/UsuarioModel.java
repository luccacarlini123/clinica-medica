package br.com.mouzetech.clinicamedica.api.model.representation;

import br.com.mouzetech.clinicamedica.domain.model.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	private Long id;

	private String nome;

	private String email;

	private String telefone;

	private TipoUsuario tipo;

	private Boolean ativo;
}