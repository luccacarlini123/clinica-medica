package br.com.mouzetech.clinicamedica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoStatusPagamento {

	CANCELADO(0, "Cancelado"),
	PENDENTE(1, "Pendente"),
	REALIZADO(2, "Realizado");

	private int codigo;
	private String descricao;
	
}
