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

import br.com.mouzetech.clinicamedica.api.model.assembler.EspecialidadeAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.EspecialidadeDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.EspecialidadeInput;
import br.com.mouzetech.clinicamedica.api.model.representation.EspecialidadeModel;
import br.com.mouzetech.clinicamedica.domain.model.Especialidade;
import br.com.mouzetech.clinicamedica.domain.repository.EspecialidadeRepository;
import br.com.mouzetech.clinicamedica.domain.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;

	@Autowired
	private EspecialidadeService especialidadeService;

	@Autowired
	private EspecialidadeAssembler especialidadeAssembler;

	@Autowired
	private EspecialidadeDisassembler especialidadeDisassembler;

	@GetMapping
	public List<EspecialidadeModel> buscarTodos() {
		return this.especialidadeAssembler.toCollectionModel(this.especialidadeRepository.findAll());
	}

	@GetMapping("/{especialidadeId}")
	public EspecialidadeModel buscarPorId(@PathVariable("especialidadeId") Long especialidadeId) {
		return this.especialidadeAssembler.toModel(this.especialidadeService.buscarPorIdOuFalhar(especialidadeId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EspecialidadeModel salvar(@RequestBody @Valid EspecialidadeInput especialidadeInput) {
		Especialidade especialidade = this.especialidadeDisassembler.toEntityFromInput(especialidadeInput);

		return this.especialidadeAssembler.toModel(this.especialidadeService.salvar(especialidade));
	}

	@DeleteMapping("/{especialidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable("especialidadeId") Long especialidadeId) {
		this.especialidadeService.excluirPorId(especialidadeId);
	}

	@PutMapping("/{especialidadeId}")
	@ResponseStatus(value = HttpStatus.OK)
	public EspecialidadeModel atualizar(@RequestBody @Valid EspecialidadeInput especialidadeInput,
			@PathVariable("especialidadeId") Long especialidadeId) {

		Especialidade especialidade = this.especialidadeService.buscarPorIdOuFalhar(especialidadeId);
		this.especialidadeDisassembler.copyFromInput(especialidade, especialidadeInput);

		return this.especialidadeAssembler.toModel(this.especialidadeService.salvar(especialidade));
	}
}