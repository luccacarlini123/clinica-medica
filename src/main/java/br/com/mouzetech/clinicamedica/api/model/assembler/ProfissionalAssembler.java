package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.ProfissionalModel;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;

@Component
public class ProfissionalAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ProfissionalModel toModel(Profissional profissional) {
		return this.modelMapper.map(profissional, ProfissionalModel.class);
	}

	public List<ProfissionalModel> toCollectionModel(List<Profissional> profissionals) {
		return profissionals.stream().map(this::toModel).collect(Collectors.toList());
	}
}