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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.CidadeAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.CidadeDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.CidadeInput;
import br.com.mouzetech.clinicamedica.api.model.representation.CidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Cidade;
import br.com.mouzetech.clinicamedica.domain.repository.CidadeRepository;
import br.com.mouzetech.clinicamedica.domain.service.CidadeService;
import br.com.mouzetech.clinicamedica.domain.service.EstadoService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeAssembler cidadeAssembler;

	@Autowired
	private CidadeDisassembler cidadeDisassembler;

	@GetMapping
	public List<CidadeModel> buscarTodos(@RequestParam(value = "estado_id", required = true) Long estadoId) {
		return this.cidadeAssembler.toCollectionModel(
				this.cidadeRepository.findByEstado(this.estadoService.buscarPorIdOuFalhar(estadoId)));
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscarPorId(@PathVariable("cidadeId") Long cidadeId) {
		return this.cidadeAssembler.toModel(this.cidadeService.buscarPorIdOuFalhar(cidadeId));
	}

	@PostMapping
	public CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = this.cidadeDisassembler.toEntityFromInput(cidadeInput);

		return this.cidadeAssembler.toModel(this.cidadeService.salvar(cidade));
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("cidadeId") Long cidadeId) {
		this.cidadeService.excluirPorId(cidadeId);
	}

	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@RequestBody @Valid CidadeInput cidadeInput,
			@PathVariable("cidadeId") Long cidadeId) {
		
		Cidade cidade = new Cidade();
		
		this.cidadeDisassembler.copyFromInput(cidade, cidadeInput);

		return this.cidadeAssembler.toModel(this.cidadeService.atualizar(cidade, cidadeId));
	}
}