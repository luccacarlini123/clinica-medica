package br.com.mouzetech.clinicamedica.api.model.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.representation.EstadoModel;
import br.com.mouzetech.clinicamedica.domain.model.Estado;

@Component
public class EstadoAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoModel toModel(Estado estado) {
		return this.modelMapper.map(estado, EstadoModel.class);
	}

	public List<EstadoModel> toCollectionModel(List<Estado> estados) {
		return estados.stream().map(this::toModel).collect(Collectors.toList());
	}
}