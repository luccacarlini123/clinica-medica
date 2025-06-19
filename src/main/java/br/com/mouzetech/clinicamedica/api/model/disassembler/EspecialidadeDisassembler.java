package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.EspecialidadeInput;
import br.com.mouzetech.clinicamedica.api.model.representation.EspecialidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Especialidade;

@Component
public class EspecialidadeDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Especialidade toEntity(EspecialidadeModel especialidade) {
		return this.modelMapper.map(especialidade, Especialidade.class);
	}
	
	public Especialidade toEntityFromInput(EspecialidadeInput especialidade) {
		return this.modelMapper.map(especialidade, Especialidade.class);
	}
	
	public void copyFromInput(Especialidade especialidade, EspecialidadeInput especialidadeInput) {
		this.modelMapper.map(especialidadeInput, especialidade);
	}

	public List<Especialidade> toCollectionEntity(List<EspecialidadeModel> especialidades) {
		return especialidades.stream().map(this::toEntity).collect(Collectors.toList());
	}
}