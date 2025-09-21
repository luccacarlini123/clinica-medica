package br.com.mouzetech.clinicamedica.api.model.input;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteConvenioInput {

	@NotNull
	@Min(1)
	private Long pacienteId;
	
	@NotNull
	@Min(1)
	private Long convenioId;
	
	@NotBlank
	private String numeroCarteirinha;
	
	@NotNull
	private LocalDate dataValidade;
}