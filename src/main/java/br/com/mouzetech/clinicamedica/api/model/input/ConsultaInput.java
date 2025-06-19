package br.com.mouzetech.clinicamedica.api.model.input;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.mouzetech.clinicamedica.domain.model.TipoStatusConsulta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaInput {

	@NotNull
	private PacienteIdInput paciente;

	@NotNull
	private AgendaIdInput agenda;

	@NotNull
	private LocalDateTime dataHora;

	@NotNull
	private TipoStatusConsulta status;

	private String observacoes;

}