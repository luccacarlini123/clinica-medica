package br.com.mouzetech.clinicamedica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.FormaPagamentoAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.FormaPagamentoDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.FormaPagamentoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.FormaPagamentoModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.model.FormaPagamento;
import br.com.mouzetech.clinicamedica.domain.repository.FormaPagamentoRepository;
import br.com.mouzetech.clinicamedica.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoAssembler formaPagamentoAssembler;

	@Autowired
	private FormaPagamentoDisassembler formaPagamentoDisassembler;

	@CheckSecurity.EstaAutenticadoParaLeitura
	@GetMapping
	public List<FormaPagamentoModel> buscarTodos() {
		return this.formaPagamentoAssembler.toCollectionModel(this.formaPagamentoRepository.findAll());
	}

	@CheckSecurity.EstaAutenticadoParaLeitura
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscarPorId(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
		return this.formaPagamentoAssembler.toModel(this.formaPagamentoService.buscarPorIdOuFalhar(formaPagamentoId));
	}

	@CheckSecurity.PodeGerenciarCadastros
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = this.formaPagamentoDisassembler.toEntityFromInput(formaPagamentoInput);

		return this.formaPagamentoAssembler.toModel(this.formaPagamentoService.salvar(formaPagamento));
	}

	@CheckSecurity.PodeGerenciarCadastros
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("formaPagamentoId") Long formaPagamentoId) {
		this.formaPagamentoService.excluirPorId(formaPagamentoId);
	}

	@CheckSecurity.PodeGerenciarCadastros
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.OK)
	public FormaPagamentoModel atualizar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput,
			@PathVariable("formaPagamentoId") Long formaPagamentoId) {

		FormaPagamento formaPagamento = this.formaPagamentoService.buscarPorIdOuFalhar(formaPagamentoId);
		this.formaPagamentoDisassembler.copyFromInput(formaPagamento, formaPagamentoInput);

		return this.formaPagamentoAssembler.toModel(this.formaPagamentoService.salvar(formaPagamento));
	}
}