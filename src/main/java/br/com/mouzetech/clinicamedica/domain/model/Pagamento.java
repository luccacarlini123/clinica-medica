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
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@OneToOne
	@JoinColumn(nullable = false)
	private Consulta consulta;

	@Column(nullable = false)
	private Double valor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@Column(nullable = true)
	private LocalDateTime dataPagamento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoStatusPagamento status;
	
	public void cancelar() {
		this.setStatus(TipoStatusPagamento.CANCELADO);
	}
	
	public void pagar() {
		this.setStatus(TipoStatusPagamento.REALIZADO);
		this.setDataPagamento(LocalDateTime.now());
	}
}