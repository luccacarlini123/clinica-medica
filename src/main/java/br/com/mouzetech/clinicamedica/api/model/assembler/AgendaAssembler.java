package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.AgendaModel;
import br.com.mouzetech.clinicamedica.domain.model.Agenda;

@Component
public class AgendaAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public AgendaModel toModel(Agenda agenda) {
		return this.modelMapper.map(agenda, AgendaModel.class);
	}

	public List<AgendaModel> toCollectionModel(List<Agenda> agendas) {
		return agendas.stream().map(this::toModel).collect(Collectors.toList());
	}
}