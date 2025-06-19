package br.com.mouzetech.clinicamedica.api.model.representation;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteConvenioModel {

	private PacienteModel paciente;

	private ConvenioModel convenio;

	private String numeroCarteirinha;

	private LocalDate dataValidade;

}