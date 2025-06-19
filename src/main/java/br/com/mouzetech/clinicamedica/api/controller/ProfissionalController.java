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

import br.com.mouzetech.clinicamedica.api.model.assembler.ProfissionalAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.ProfissionalDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.ProfissionalInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ProfissionalModel;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;
import br.com.mouzetech.clinicamedica.domain.repository.ProfissionalRepository;
import br.com.mouzetech.clinicamedica.domain.service.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ProfissionalAssembler profissionalAssembler;

	@Autowired
	private ProfissionalDisassembler profissionalDisassembler;

	@GetMapping
	public List<ProfissionalModel> buscarTodos() {
		return this.profissionalAssembler.toCollectionModel(this.profissionalRepository.findAll());
	}

	@GetMapping("/{profissionalId}")
	public ProfissionalModel buscarPorId(@PathVariable("profissionalId") Long profissionalId) {
		return this.profissionalAssembler.toModel(this.profissionalService.buscarPorIdOuFalhar(profissionalId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProfissionalModel salvar(@RequestBody @Valid ProfissionalInput profissionalInput) {
		Profissional profissional = this.profissionalDisassembler.toEntityFromInput(profissionalInput);

		return this.profissionalAssembler.toModel(this.profissionalService.salvarOuAtualizar(profissional, null));
	}

	@DeleteMapping("/{profissionalId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("profissionalId") Long profissionalId) {
		this.profissionalService.excluirPorId(profissionalId);
	}

	@PutMapping("/{profissionalId}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProfissionalModel atualizar(@RequestBody @Valid ProfissionalInput profissionalInput,
			@PathVariable("profissionalId") Long profissionalId) {

		Profissional profissional = new Profissional();
		this.profissionalDisassembler.copyFromInput(profissional, profissionalInput);

		return this.profissionalAssembler.toModel(this.profissionalService.salvarOuAtualizar(profissional, profissionalId));
	}
}