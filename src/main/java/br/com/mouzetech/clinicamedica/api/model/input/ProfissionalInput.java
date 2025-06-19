package br.com.mouzetech.clinicamedica.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.mouzetech.clinicamedica.domain.model.TipoGeneroPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalInput {

	@NotNull
	@Valid
	private UsuarioInput usuario;

	@NotNull
	@Valid
	private EspecialidadeIdInput especialidade;

	private String crm;

	@NotNull
	private TipoGeneroPessoa generoPessoa;

	@NotNull
	private boolean convenioParticipa;

	@NotNull
	@Valid
	private EnderecoInput endereco;
}