package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.GrupoModel;
import br.com.mouzetech.clinicamedica.domain.model.Grupo;

@Component
public class GrupoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public GrupoModel toModel(Grupo grupo) {
		return this.modelMapper.map(grupo, GrupoModel.class);
	}

	public List<GrupoModel> toCollectionModel(List<Grupo> permissoes) {
		return permissoes.stream().map(this::toModel).collect(Collectors.toList());
	}
}