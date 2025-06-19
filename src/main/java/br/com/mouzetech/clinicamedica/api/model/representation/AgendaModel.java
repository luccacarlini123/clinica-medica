package br.com.mouzetech.clinicamedica.api.model.representation;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaModel {

	private Long id;

	private ProfissionalModel medico;

	private LocalDate data;

	private LocalTime horaInicio;

	private LocalTime horaFim;
}