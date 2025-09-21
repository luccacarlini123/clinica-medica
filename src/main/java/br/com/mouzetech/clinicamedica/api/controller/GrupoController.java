package br.com.mouzetech.clinicamedica.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.GrupoAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.GrupoDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.GrupoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.GrupoModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.model.Grupo;
import br.com.mouzetech.clinicamedica.domain.repository.GrupoRepository;
import br.com.mouzetech.clinicamedica.domain.service.GrupoService;
import jakarta.validation.Valid;

@CheckSecurity.PodeGerenciarCadastros
@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private GrupoAssembler grupoAssembler;

	@Autowired
	private GrupoDisassembler grupoDisassembler;

	@GetMapping
	public List<GrupoModel> buscarTodos() {
		return this.grupoAssembler.toCollectionModel(this.grupoRepository.findAll());
	}

	@GetMapping("/{id}")
	public GrupoModel buscarPorId(@PathVariable Long id) {
		return this.grupoAssembler.toModel(this.grupoService.buscarPorIdOuFalhar(id));
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		this.grupoRepository.delete(this.grupoService.buscarPorIdOuFalhar(id));
	}

	@PostMapping
	public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {

		Grupo grupo = this.grupoRepository.save(this.grupoDisassembler.toEntityFromInput(grupoInput));

		return this.grupoAssembler.toModel(grupo);
	}

	@PutMapping("/{id}")
	public GrupoModel atualizar(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long id) {

		Grupo grupoEncontrada = this.grupoService.buscarPorIdOuFalhar(id);

		this.grupoDisassembler.copyFromInput(grupoEncontrada, grupoInput);

		this.grupoRepository.save(grupoEncontrada);

		return this.grupoAssembler.toModel(grupoEncontrada);
	}

	@PutMapping("/{idGrupo}/permissao/{idPermissao}")
	public void associarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
		this.grupoService.associarPermissao(idGrupo, idPermissao);
	}

	@DeleteMapping("/{idGrupo}/permissao/{idPermissao}")
	public void desassociarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao) {
		this.grupoService.desassociarPermissao(idGrupo, idPermissao);
	}
}