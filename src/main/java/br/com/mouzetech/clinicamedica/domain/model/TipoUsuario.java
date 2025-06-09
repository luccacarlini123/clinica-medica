package br.com.mouzetech.clinicamedica.domain.model;

public enum TipoUsuario {

	ADMIN(0, "Admin"),
	PROFISSIONAL(1, "Profissional"),
	RECEPCIONISTA(2, "Recepcionista"),
	PACIENTE(3, "Paciente");

	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private int codigo;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}