package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.AgendaInput;
import br.com.mouzetech.clinicamedica.api.model.representation.AgendaModel;
import br.com.mouzetech.clinicamedica.domain.model.Agenda;

@Component
public class AgendaDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Agenda toEntity(AgendaModel agenda) {
		return this.modelMapper.map(agenda, Agenda.class);
	}

	public Agenda toEntityFromInput(AgendaInput agenda) {
		return this.modelMapper.map(agenda, Agenda.class);
	}

	public void copyFromInput(Agenda agenda, AgendaInput agendaInput) {
		this.modelMapper.map(agendaInput, agenda);
	}

	public List<Agenda> toCollectionEntity(List<AgendaModel> agendas) {
		return agendas.stream().map(this::toEntity).collect(Collectors.toList());
	}
}