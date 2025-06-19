package br.com.mouzetech.clinicamedica.api.model.representation;

import java.time.LocalDate;

import br.com.mouzetech.clinicamedica.domain.model.TipoGeneroPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteModel {

	private Long id;

	private UsuarioModel usuario;

	private LocalDate dataNascimento;

	private String cpf;

	private TipoGeneroPessoa generoPessoa;

	private String nome;

	private EnderecoModel endereco;

}