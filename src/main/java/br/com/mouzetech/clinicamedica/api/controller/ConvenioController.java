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

import br.com.mouzetech.clinicamedica.api.model.assembler.ConvenioAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.ConvenioDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.ConvenioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ConvenioModel;
import br.com.mouzetech.clinicamedica.domain.model.Convenio;
import br.com.mouzetech.clinicamedica.domain.repository.ConvenioRepository;
import br.com.mouzetech.clinicamedica.domain.service.ConvenioService;

@RestController
@RequestMapping("/convenios")
public class ConvenioController {

	@Autowired
	private ConvenioRepository convenioRepository;

	@Autowired
	private ConvenioService convenioService;

	@Autowired
	private ConvenioAssembler convenioAssembler;

	@Autowired
	private ConvenioDisassembler convenioDisassembler;

	@GetMapping
	public List<ConvenioModel> buscarTodos() {
		return this.convenioAssembler.toCollectionModel(this.convenioRepository.findAll());
	}

	@GetMapping("/{convenioId}")
	public ConvenioModel buscarPorId(@PathVariable("convenioId") Long convenioId) {
		return this.convenioAssembler.toModel(this.convenioService.buscarPorIdOuFalhar(convenioId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ConvenioModel salvar(@RequestBody @Valid ConvenioInput convenioInput) {
		Convenio convenio = this.convenioDisassembler.toEntityFromInput(convenioInput);

		return this.convenioAssembler.toModel(this.convenioService.salvar(convenio));
	}

	@DeleteMapping("/{convenioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("convenioId") Long convenioId) {
		this.convenioService.excluirPorId(convenioId);
	}

	@PutMapping("/{convenioId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ConvenioModel atualizar(@RequestBody @Valid ConvenioInput convenioInput,
			@PathVariable("convenioId") Long convenioId) {

		Convenio convenio = this.convenioService.buscarPorIdOuFalhar(convenioId);
		this.convenioDisassembler.copyFromInput(convenio, convenioInput);

		return this.convenioAssembler.toModel(this.convenioService.salvar(convenio));
	}
}