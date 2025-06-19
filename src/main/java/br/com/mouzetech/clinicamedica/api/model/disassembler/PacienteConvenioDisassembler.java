package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.PacienteConvenioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PacienteConvenioModel;
import br.com.mouzetech.clinicamedica.domain.model.PacienteConvenio;

@Component
public class PacienteConvenioDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public PacienteConvenio toEntity(PacienteConvenioModel pacienteConvenio) {
		return this.modelMapper.map(pacienteConvenio, PacienteConvenio.class);
	}

	public PacienteConvenio toEntityFromInput(PacienteConvenioInput pacienteConvenio) {
		return this.modelMapper.map(pacienteConvenio, PacienteConvenio.class);
	}

	public void copyFromInput(PacienteConvenio pacienteConvenio, PacienteConvenioInput pacienteConvenioInput) {
		this.modelMapper.map(pacienteConvenioInput, pacienteConvenio);
	}

	public List<PacienteConvenio> toCollectionEntity(List<PacienteConvenioModel> pacienteConvenios) {
		return pacienteConvenios.stream().map(this::toEntity).collect(Collectors.toList());
	}
}