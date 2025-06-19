package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.ProfissionalInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ProfissionalModel;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;

@Component
public class ProfissionalDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Profissional toEntity(ProfissionalModel profissional) {
		return this.modelMapper.map(profissional, Profissional.class);
	}
	
	public Profissional toEntityFromInput(ProfissionalInput profissional) {
		return this.modelMapper.map(profissional, Profissional.class);
	}
	
	public void copyFromInput(Profissional profissional, ProfissionalInput profissionalInput) {
		this.modelMapper.map(profissionalInput, profissional);
	}
	
	public void copyFromEntity(Profissional profissionalASerAtualizado, Profissional profissionalNovo) {
		this.modelMapper.map(profissionalNovo, profissionalASerAtualizado);
	}

	public List<Profissional> toCollectionEntity(List<ProfissionalModel> profissionals) {
		return profissionals.stream().map(this::toEntity).collect(Collectors.toList());
	}
}