package br.com.mouzetech.clinicamedica.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.AgendaAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.AgendaDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.AgendaInput;
import br.com.mouzetech.clinicamedica.api.model.representation.AgendaModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.service.AgendaService;
import jakarta.validation.Valid;

@CheckSecurity.PodeGerenciarAgendasConsultas
@RestController
@RequestMapping("/agendas")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private AgendaDisassembler agendaDisassembler;

	@Autowired
	private AgendaAssembler agendaAssembler;

	@PostMapping
	public AgendaModel criarAgenda(@RequestBody @Valid AgendaInput agendaInput) {
		return this.agendaAssembler
				.toModel(this.agendaService.criarAgenda(this.agendaDisassembler.toEntityFromInput(agendaInput)));
	}

	@GetMapping("/medicos/{medicoId}")
	public List<AgendaModel> buscarPorMedicoEOpcionalmenteData(@PathVariable("medicoId") Long medicoId,
			@RequestParam(name = "datainicio", required = false) LocalDate dataInicio, @RequestParam(name = "datafim", required = false) LocalDate dataFim) {

		return this.agendaAssembler
				.toCollectionModel(this.agendaService.buscarPorMedicoEOpcionalmenteData(medicoId, dataInicio, dataFim));
	}
}