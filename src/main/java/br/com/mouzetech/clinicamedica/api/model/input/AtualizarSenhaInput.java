package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarSenhaInput {

	@NotBlank
	public String senhaAtual;

	@NotBlank
	private String senhaNova;

}