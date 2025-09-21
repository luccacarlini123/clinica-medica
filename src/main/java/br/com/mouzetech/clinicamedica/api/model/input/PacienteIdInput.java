package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteIdInput {

	@NotNull
	private Long id;
	
}