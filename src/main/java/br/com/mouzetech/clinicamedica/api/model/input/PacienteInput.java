package br.com.mouzetech.clinicamedica.api.model.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.mouzetech.clinicamedica.domain.model.TipoGeneroPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteInput {

	private UsuarioInput usuario;

	@NotNull
	private LocalDate dataNascimento;

	@CPF
	@NotBlank
	private String cpf;

	@NotNull
	private TipoGeneroPessoa generoPessoa;

	@NotBlank
	private String nome;

	private EnderecoInput endereco;
}