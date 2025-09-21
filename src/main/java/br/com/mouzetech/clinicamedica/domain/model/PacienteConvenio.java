package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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