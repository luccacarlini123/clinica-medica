package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.PacienteConvenioModel;
import br.com.mouzetech.clinicamedica.domain.model.PacienteConvenio;

@Component
public class PacienteConvenioAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PacienteConvenioModel toModel(PacienteConvenio pacienteConvenio) {
		return this.modelMapper.map(pacienteConvenio, PacienteConvenioModel.class);
	}

	public List<PacienteConvenioModel> toCollectionModel(List<PacienteConvenio> pacienteConvenios) {
		return pacienteConvenios.stream().map(this::toModel).collect(Collectors.toList());
	}
}