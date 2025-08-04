package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaInput {

	@Valid
	@NotNull
	private PacienteIdInput paciente;

	@Valid
	@NotNull
	private AgendaIdInput agenda;

	@NotNull
	@Min(1)
	private Double valor;

	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;

	private String observacoes;
}