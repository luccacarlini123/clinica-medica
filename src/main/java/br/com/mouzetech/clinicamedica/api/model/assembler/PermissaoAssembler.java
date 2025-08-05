package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.PermissaoModel;
import br.com.mouzetech.clinicamedica.domain.model.Permissao;

@Component
public class PermissaoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoModel toModel(Permissao permissao) {
		return this.modelMapper.map(permissao, PermissaoModel.class);
	}

	public List<PermissaoModel> toCollectionModel(List<Permissao> permissoes) {
		return permissoes.stream().map(this::toModel).collect(Collectors.toList());
	}
}