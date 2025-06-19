package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.ConsultaModel;
import br.com.mouzetech.clinicamedica.domain.model.Consulta;

@Component
public class ConsultaAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ConsultaModel toModel(Consulta consulta) {
		return this.modelMapper.map(consulta, ConsultaModel.class);
	}

	public List<ConsultaModel> toCollectionModel(List<Consulta> consultas) {
		return consultas.stream().map(this::toModel).collect(Collectors.toList());
	}
}