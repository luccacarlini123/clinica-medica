package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.PacienteInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PacienteModel;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;

@Component
public class PacienteDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Paciente toEntity(PacienteModel paciente) {
		return this.modelMapper.map(paciente, Paciente.class);
	}

	public Paciente toEntityFromInput(PacienteInput paciente) {
		return this.modelMapper.map(paciente, Paciente.class);
	}

	public void copyFromInput(Paciente paciente, PacienteInput pacienteInput) {
		this.modelMapper.map(pacienteInput, paciente);
	}

	public void copyFromEntity(Paciente pacienteASerAtualizado, Paciente pacienteNovo) {
		this.modelMapper.map(pacienteNovo, pacienteASerAtualizado);
	}

	public List<Paciente> toCollectionEntity(List<PacienteModel> pacientes) {
		return pacientes.stream().map(this::toEntity).collect(Collectors.toList());
	}
}