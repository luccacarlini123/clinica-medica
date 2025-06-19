package br.com.mouzetech.clinicamedica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.PacienteConvenioAssembler;
import br.com.mouzetech.clinicamedica.api.model.input.PacienteConvenioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PacienteConvenioModel;
import br.com.mouzetech.clinicamedica.domain.service.PacienteConvenioService;

@RestController
@RequestMapping("/pacientes")
public class PacienteConvenioController {
	
	@Autowired
	private PacienteConvenioService pacienteConvenioService;

	@Autowired
	private PacienteConvenioAssembler pacienteConvenioAssembler;

	@PutMapping("/convenios")
	public PacienteConvenioModel salvar(@RequestBody @Valid PacienteConvenioInput pacienteConvenioInput) {
		return this.pacienteConvenioAssembler.toModel(this.pacienteConvenioService.salvar(pacienteConvenioInput));
	}
	
	@GetMapping("/{idPaciente}/convenios")
	public List<PacienteConvenioModel> buscarConveniosDoPaciente(@PathVariable("idPaciente") Long pacienteId) {
		return this.pacienteConvenioAssembler
				.toCollectionModel(this.pacienteConvenioService.buscarConveniosDoPaciente(pacienteId));
	}

}