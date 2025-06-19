package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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