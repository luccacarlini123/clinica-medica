package br.com.mouzetech.clinicamedica.api.model.representation;

import java.time.LocalDateTime;

import br.com.mouzetech.clinicamedica.domain.model.TipoStatusConsulta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaModel {

	private Long id;

	private PacienteModel paciente;

	private AgendaModel agenda;

	private LocalDateTime dataHora;

	private TipoStatusConsulta status;

	private String observacoes;
}