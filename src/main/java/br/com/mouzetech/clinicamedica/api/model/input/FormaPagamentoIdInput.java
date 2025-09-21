package br.com.mouzetech.clinicamedica.api.model.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

	@Min(1)
	@NotNull
	private Long id;

}