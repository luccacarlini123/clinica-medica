package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.ConsultaInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ConsultaModel;
import br.com.mouzetech.clinicamedica.domain.model.Consulta;

@Component
public class ConsultaDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Consulta toEntity(ConsultaModel consulta) {
		return this.modelMapper.map(consulta, Consulta.class);
	}

	public Consulta toEntityFromInput(ConsultaInput consulta) {
		return this.modelMapper.map(consulta, Consulta.class);
	}

	public void copyFromInput(Consulta consulta, ConsultaInput consultaInput) {
		this.modelMapper.map(consultaInput, consulta);
	}

	public void copyFromEntity(Consulta consultaExistente, Consulta consultaNova) {
		this.modelMapper.map(consultaNova, consultaExistente);
	}

	public List<Consulta> toCollectionEntity(List<ConsultaModel> consultas) {
		return consultas.stream().map(this::toEntity).collect(Collectors.toList());
	}
}