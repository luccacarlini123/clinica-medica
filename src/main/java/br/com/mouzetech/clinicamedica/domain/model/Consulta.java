package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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