package br.com.mouzetech.clinicamedica.api.model.input;

import br.com.mouzetech.clinicamedica.domain.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@NotBlank
	private String nome;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@NotBlank
	private String telefone;

	@NotNull
	private TipoUsuario tipo;
}