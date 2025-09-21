package br.com.mouzetech.clinicamedica.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.ConsultaAssembler;
import br.com.mouzetech.clinicamedica.api.model.input.ConsultaInput;
import br.com.mouzetech.clinicamedica.api.model.representation.ConsultaModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.service.ConsultaService;
import jakarta.validation.Valid;

@CheckSecurity.PodeGerenciarAgendasConsultas
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ConsultaAssembler consultaAssembler;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ConsultaModel agendar(@RequestBody @Valid ConsultaInput consultaInput) {
		return this.consultaAssembler.toModel(this.consultaService.agendar(consultaInput));
	}
	
	@DeleteMapping("/{idConsulta}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desmarcar(@PathVariable("idConsulta") Long idConsulta) {
		this.consultaService.desmarcarConsulta(idConsulta);
	}
	
	@PutMapping("/{idConsulta}/finalizar")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable("idConsulta") Long idConsulta) {
		this.consultaService.finalizarConsulta(idConsulta);
	}
	
	@PutMapping("/{idConsulta}/pagar")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void pagar(@PathVariable("idConsulta") Long idConsulta) {
		this.consultaService.pagarConsulta(idConsulta);
	}
}