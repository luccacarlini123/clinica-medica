package br.com.mouzetech.clinicamedica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoGeneroPessoa {

	OUTRO(0, "Outro"),
	MASCULINO(1, "Masculino"),
	FEMININO(2, "Feminino");

	private int id;
	private String descricao;
}