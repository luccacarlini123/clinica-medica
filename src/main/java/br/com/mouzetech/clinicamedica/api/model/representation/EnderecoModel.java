package br.com.mouzetech.clinicamedica.api.model.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private Long id;

	private String logradouro;

	private String numero;

	private String bairro;

	private String cep;

	private String complemento;

	private CidadeModel cidade;

}