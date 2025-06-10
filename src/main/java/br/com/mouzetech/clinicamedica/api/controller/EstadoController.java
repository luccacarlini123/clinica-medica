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

import br.com.mouzetech.clinicamedica.api.model.assembler.EstadoAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.EstadoDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.EstadoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.EstadoModel;
import br.com.mouzetech.clinicamedica.domain.model.Estado;
import br.com.mouzetech.clinicamedica.domain.repository.EstadoRepository;
import br.com.mouzetech.clinicamedica.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private EstadoAssembler estadoAssembler;

	@Autowired
	private EstadoDisassembler estadoDisassembler;

	@GetMapping
	public List<EstadoModel> buscarTodos() {
		return this.estadoAssembler.toCollectionModel(this.estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscarPorId(@PathVariable("estadoId") Long estadoId) {
		return this.estadoAssembler.toModel(this.estadoService.buscarPorIdOuFalhar(estadoId));
	}

	@PostMapping
	public EstadoModel salvar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = this.estadoDisassembler.toEntityFromInput(estadoInput);

		return this.estadoAssembler.toModel(this.estadoService.salvar(estado));
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("estadoId") Long estadoId) {
		this.estadoService.excluirPorId(estadoId);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@RequestBody @Valid EstadoInput estadoInput, @PathVariable("estadoId") Long estadoId) {
		
		Estado estado = this.estadoService.buscarPorIdOuFalhar(estadoId);
		this.estadoDisassembler.copyFromInput(estado, estadoInput);

		return this.estadoAssembler.toModel(this.estadoService.salvar(estado));
	}
}