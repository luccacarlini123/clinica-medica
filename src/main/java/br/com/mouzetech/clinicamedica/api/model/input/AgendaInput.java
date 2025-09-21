package br.com.mouzetech.clinicamedica.api.model.input;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaInput {

	@Valid
	@NotNull
	private ProfissionalIdInput medico;

	@NotNull
    private LocalDate data;

	@NotNull
    private LocalTime horaInicio;

	@NotNull
    private LocalTime horaFim;	
}