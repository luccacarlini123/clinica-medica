package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.CidadeInput;
import br.com.mouzetech.clinicamedica.api.model.representation.CidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Cidade;

@Component
public class CidadeDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Cidade toEntity(CidadeModel cidade) {
		return this.modelMapper.map(cidade, Cidade.class);
	}
	
	public Cidade toEntityFromInput(CidadeInput cidade) {
		return this.modelMapper.map(cidade, Cidade.class);
	}
	
	public void copyFromInput(Cidade cidade, CidadeInput cidadeInput) {
		this.modelMapper.map(cidadeInput, cidade);
	}
	
	public void copyFromEntity(Cidade cidadeExistente, Cidade cidadeNova) {
		this.modelMapper.map(cidadeNova, cidadeExistente);
	}

	public List<Cidade> toCollectionEntity(List<CidadeModel> cidades) {
		return cidades.stream().map(this::toEntity).collect(Collectors.toList());
	}
}