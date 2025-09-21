package br.com.mouzetech.clinicamedica.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
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