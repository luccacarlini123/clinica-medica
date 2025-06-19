package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.EspecialidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Especialidade;

@Component
public class EspecialidadeAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EspecialidadeModel toModel(Especialidade especialidade) {
		return this.modelMapper.map(especialidade, EspecialidadeModel.class);
	}

	public List<EspecialidadeModel> toCollectionModel(List<Especialidade> especialidades) {
		return especialidades.stream().map(this::toModel).collect(Collectors.toList());
	}
}