package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@Min(1)
	@NotNull
	private Long id;

}