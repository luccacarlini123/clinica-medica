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
import javax.persistence.PrePersist;

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
	@JoinColumn(nullable = true, unique = true)
	private Agenda agenda;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoStatusConsulta status;

	@Column(nullable = true)
	private String observacoes;
	
	@Column(nullable = false)
	private LocalDateTime dataHoraRegistro;
	
	@PrePersist
	public void prePersist() {
		this.dataHoraRegistro = LocalDateTime.now();
	}
	
	public void desmarcar() {
		this.setAgenda(null);
		this.setStatus(TipoStatusConsulta.CANCELADA);
	}
	
	public boolean jaFoiRealizada() {
		return TipoStatusConsulta.REALIZADA.equals(this.getStatus());
	}
	
	public boolean jaFoiDesmarcada() {
		return TipoStatusConsulta.CANCELADA.equals(this.getStatus());
	}
	
	public void finalizar() {
		this.setStatus(TipoStatusConsulta.REALIZADA);
	}

}