package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(nullable = false, unique = true)
	private Agenda agenda;

	@Column(nullable = false, name = "data_hora")
	private LocalDateTime dataHora;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoStatusConsulta status;

	@Column(nullable = true)
	private String observacoes;

}