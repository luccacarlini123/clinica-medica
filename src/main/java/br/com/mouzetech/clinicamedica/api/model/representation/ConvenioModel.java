package br.com.mouzetech.clinicamedica.api.model.representation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvenioModel {

	private Long id;
	
	private String nome;

	private String cnpj;

	private String email;

	private String telefone;
}