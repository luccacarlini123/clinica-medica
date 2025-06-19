package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mouzetech.clinicamedica.domain.model.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@NotBlank
	private String telefone;

	@NotNull
	private TipoUsuario tipo;

	private boolean ativo;
}