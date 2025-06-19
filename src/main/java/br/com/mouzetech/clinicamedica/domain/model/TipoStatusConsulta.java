package br.com.mouzetech.clinicamedica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoStatusConsulta {

	AGENDADA(0, "Agendada"),
	REALIZADA(1, "Realizada"),
	CANCELADA(2, "Cancelada");

	private int codigo;
	private String descricao;
}