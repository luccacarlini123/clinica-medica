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

import br.com.mouzetech.clinicamedica.api.model.assembler.PacienteAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.PacienteDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.PacienteInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PacienteModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;
import br.com.mouzetech.clinicamedica.domain.repository.PacienteRepository;
import br.com.mouzetech.clinicamedica.domain.service.PacienteService;

@CheckSecurity.PodeGerenciarPacientes
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private PacienteAssembler pacienteAssembler;

	@Autowired
	private PacienteDisassembler pacienteDisassembler;

	@GetMapping
	public List<PacienteModel> buscarTodos() {
		return this.pacienteAssembler.toCollectionModel(this.pacienteRepository.findAll());
	}

	@GetMapping("/{pacienteId}")
	public PacienteModel buscarPorId(@PathVariable("pacienteId") Long pacienteId) {
		return this.pacienteAssembler.toModel(this.pacienteService.buscarPorIdOuFalhar(pacienteId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PacienteModel salvar(@RequestBody @Valid PacienteInput pacienteInput) {
		Paciente paciente = this.pacienteDisassembler.toEntityFromInput(pacienteInput);

		return this.pacienteAssembler.toModel(this.pacienteService.salvarOuAtualizar(paciente, null));
	}

	@DeleteMapping("/{pacienteId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("pacienteId") Long pacienteId) {
		this.pacienteService.excluirPorId(pacienteId);
	}
	
	@PutMapping("/{pacienteId}")
	@ResponseStatus(value = HttpStatus.OK)
	public PacienteModel atualizar(@RequestBody @Valid PacienteInput pacienteInput, @PathVariable(name = "pacienteId", required = true) Long pacienteId) {
		
		Paciente paciente = this.pacienteDisassembler.toEntityFromInput(pacienteInput);

		return this.pacienteAssembler.toModel(this.pacienteService.salvarOuAtualizar(paciente, pacienteId));
	}
}