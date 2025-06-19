package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.PacienteModel;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;

@Component
public class PacienteAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PacienteModel toModel(Paciente paciente) {
		return this.modelMapper.map(paciente, PacienteModel.class);
	}

	public List<PacienteModel> toCollectionModel(List<Paciente> pacientes) {
		return pacientes.stream().map(this::toModel).collect(Collectors.toList());
	}
}