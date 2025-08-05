package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.GrupoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.GrupoModel;
import br.com.mouzetech.clinicamedica.domain.model.Grupo;

@Component
public class GrupoDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Grupo toEntity(GrupoModel grupo) {
		return this.modelMapper.map(grupo, Grupo.class);
	}

	public Grupo toEntityFromInput(GrupoInput grupo) {
		return this.modelMapper.map(grupo, Grupo.class);
	}

	public void copyFromInput(Grupo grupo, GrupoInput grupoInput) {
		this.modelMapper.map(grupoInput, grupo);
	}

	public void copyFromEntity(Grupo grupoExistente, Grupo grupoNova) {
		this.modelMapper.map(grupoNova, grupoExistente);
	}

	public List<Grupo> toCollectionEntity(List<GrupoModel> permissoes) {
		return permissoes.stream().map(this::toEntity).collect(Collectors.toList());
	}
}