package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.PermissaoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PermissaoModel;
import br.com.mouzetech.clinicamedica.domain.model.Permissao;

@Component
public class PermissaoDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Permissao toEntity(PermissaoModel permissao) {
		return this.modelMapper.map(permissao, Permissao.class);
	}

	public Permissao toEntityFromInput(PermissaoInput permissao) {
		return this.modelMapper.map(permissao, Permissao.class);
	}

	public void copyFromInput(Permissao permissao, PermissaoInput permissaoInput) {
		this.modelMapper.map(permissaoInput, permissao);
	}

	public void copyFromEntity(Permissao permissaoExistente, Permissao permissaoNova) {
		this.modelMapper.map(permissaoNova, permissaoExistente);
	}

	public List<Permissao> toCollectionEntity(List<PermissaoModel> permissoes) {
		return permissoes.stream().map(this::toEntity).collect(Collectors.toList());
	}
}