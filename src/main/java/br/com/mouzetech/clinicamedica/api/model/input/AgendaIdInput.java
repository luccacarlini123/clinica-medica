package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaIdInput {

	@NotNull
	private Long id;
	
}