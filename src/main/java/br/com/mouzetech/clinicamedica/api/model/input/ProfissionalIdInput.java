package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalIdInput {

	@NotNull
	private Long id;
	
}