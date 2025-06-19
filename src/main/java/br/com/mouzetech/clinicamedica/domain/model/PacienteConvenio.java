package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PacienteConvenio {

	@EmbeddedId
	private PacienteConvenioKey id;
	
	@MapsId("pacienteId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "paciente_id")
	private Paciente paciente;
	
	@MapsId("convenioId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "convenio_id")
	private Convenio convenio;
	
	@Column(nullable = false)
	private String numeroCarteirinha;
	
	@Column(nullable = false)
	private LocalDate dataValidade;
	
}