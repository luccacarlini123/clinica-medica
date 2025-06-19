package br.com.mouzetech.clinicamedica.api.model.representation;

import br.com.mouzetech.clinicamedica.domain.model.TipoGeneroPessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalModel {

	private Long id;

	private UsuarioModel usuario;

	private EspecialidadeModel especialidade;

	private String crm;

	private TipoGeneroPessoa generoPessoa;

	private boolean convenioParticipa;

	private EnderecoModel endereco;
}