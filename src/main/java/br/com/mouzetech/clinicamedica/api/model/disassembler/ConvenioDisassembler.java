package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.ConvenioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ConvenioModel;
import br.com.mouzetech.clinicamedica.domain.model.Convenio;

@Component
public class ConvenioDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Convenio toEntity(ConvenioModel convenio) {
		return this.modelMapper.map(convenio, Convenio.class);
	}

	public Convenio toEntityFromInput(ConvenioInput convenio) {
		return this.modelMapper.map(convenio, Convenio.class);
	}

	public void copyFromInput(Convenio convenio, ConvenioInput convenioInput) {
		this.modelMapper.map(convenioInput, convenio);
	}

	public List<Convenio> toCollectionEntity(List<ConvenioModel> convenios) {
		return convenios.stream().map(this::toEntity).collect(Collectors.toList());
	}
}