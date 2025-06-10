package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.EstadoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.EstadoModel;
import br.com.mouzetech.clinicamedica.domain.model.Estado;

@Component
public class EstadoDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Estado toEntity(EstadoModel estado) {
		return this.modelMapper.map(estado, Estado.class);
	}
	
	public Estado toEntityFromInput(EstadoInput estado) {
		return this.modelMapper.map(estado, Estado.class);
	}
	
	public void copyFromInput(Estado estado, EstadoInput estadoInput) {
		this.modelMapper.map(estadoInput, estado);
	}

	public List<Estado> toCollectionEntity(List<EstadoModel> estados) {
		return estados.stream().map(this::toEntity).collect(Collectors.toList());
	}
}