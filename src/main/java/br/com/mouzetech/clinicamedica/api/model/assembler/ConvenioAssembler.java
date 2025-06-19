package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.ConvenioModel;
import br.com.mouzetech.clinicamedica.domain.model.Convenio;

@Component
public class ConvenioAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ConvenioModel toModel(Convenio convenio) {
		return this.modelMapper.map(convenio, ConvenioModel.class);
	}

	public List<ConvenioModel> toCollectionModel(List<Convenio> convenios) {
		return convenios.stream().map(this::toModel).collect(Collectors.toList());
	}
}