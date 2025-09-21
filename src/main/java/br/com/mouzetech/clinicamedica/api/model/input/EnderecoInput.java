package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;

	@NotBlank
	private String bairro;

	@NotBlank
	private String cep;

	private String complemento;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

}