package br.com.mouzetech.clinicamedica.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PacienteConvenioKey implements Serializable {

	private static final long serialVersionUID = 8106849764543292984L;

	@Column(name = "paciente_id", nullable = false)
	private Long pacienteId;

	@Column(name = "convenio_id", nullable = false)
	private Long convenioId;
}