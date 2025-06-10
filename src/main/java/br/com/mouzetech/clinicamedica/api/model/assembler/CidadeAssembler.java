package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.CidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Cidade;

@Component
public class CidadeAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		return this.modelMapper.map(cidade, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream().map(this::toModel).collect(Collectors.toList());
	}
}