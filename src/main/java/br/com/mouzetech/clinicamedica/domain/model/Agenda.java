package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "agenda", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "medico_id", "data", "hora_inicio", "hora_fim" }) })
public class Agenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(name = "medico_id", nullable = false)
	private Profissional medico;

	@Column(name = "data", nullable = false)
	private LocalDate data;

	@Column(name = "hora_inicio", nullable = false)
	private LocalTime horaInicio;

	@Column(name = "hora_fim", nullable = false)
	private LocalTime horaFim;
}