package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvenioInput {

	@NotBlank
	private String nome;

	private String cnpj;

	private String email;

	private String telefone;
}