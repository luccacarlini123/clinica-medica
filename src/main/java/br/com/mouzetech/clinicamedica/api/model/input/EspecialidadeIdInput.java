package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EspecialidadeIdInput {

	@NotNull
	private Long id;
	
}
