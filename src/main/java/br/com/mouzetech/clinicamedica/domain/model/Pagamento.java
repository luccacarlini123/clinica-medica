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
import jakarta.persistence.OneToOne;
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