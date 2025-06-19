package br.com.mouzetech.clinicamedica.api.model.disassembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mouzetech.clinicamedica.api.model.input.FormaPagamentoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.FormaPagamentoModel;
import br.com.mouzetech.clinicamedica.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamento toEntity(FormaPagamentoModel formaPagamento) {
		return this.modelMapper.map(formaPagamento, FormaPagamento.class);
	}
	
	public FormaPagamento toEntityFromInput(FormaPagamentoInput formaPagamento) {
		return this.modelMapper.map(formaPagamento, FormaPagamento.class);
	}
	
	public void copyFromInput(FormaPagamento formaPagamento, FormaPagamentoInput formaPagamentoInput) {
		this.modelMapper.map(formaPagamentoInput, formaPagamento);
	}

	public List<FormaPagamento> toCollectionEntity(List<FormaPagamentoModel> formaPagamentos) {
		return formaPagamentos.stream().map(this::toEntity).collect(Collectors.toList());
	}
}