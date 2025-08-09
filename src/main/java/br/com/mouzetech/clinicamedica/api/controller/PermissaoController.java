package br.com.mouzetech.clinicamedica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.api.model.assembler.PermissaoAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.PermissaoDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.PermissaoInput;
import br.com.mouzetech.clinicamedica.api.model.representation.PermissaoModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.model.Permissao;
import br.com.mouzetech.clinicamedica.domain.repository.PermissaoRepository;
import br.com.mouzetech.clinicamedica.domain.service.PermissaoService;

@CheckSecurity.PodeGerenciarCadastros
@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@Autowired
	private PermissaoAssembler permissaoAssembler;
	
	@Autowired
	private PermissaoDisassembler permissaoDisassembler;
	
	@GetMapping
	public List<PermissaoModel> buscarTodos() {
		return this.permissaoAssembler.toCollectionModel(this.permissaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public PermissaoModel buscarPorId(@PathVariable Long id) {
		return this.permissaoAssembler.toModel(this.permissaoService.buscarPorIdOuFalhar(id));
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		this.permissaoRepository.delete(this.permissaoService.buscarPorIdOuFalhar(id));
	}
	
	@PostMapping
	public PermissaoModel salvar(@RequestBody @Valid PermissaoInput permissaoInput) {

		Permissao permissao = this.permissaoRepository
				.save(this.permissaoDisassembler.toEntityFromInput(permissaoInput));
		
		return this.permissaoAssembler.toModel(permissao);
	}
	
	@PutMapping("/{id}")
	public PermissaoModel atualizar(@RequestBody @Valid PermissaoInput permissaoInput, @PathVariable Long id) {

		Permissao permissaoEncontrada = this.permissaoService.buscarPorIdOuFalhar(id);
		
		this.permissaoDisassembler.copyFromInput(permissaoEncontrada, permissaoInput);
		
		this.permissaoRepository.save(permissaoEncontrada);
		
		return this.permissaoAssembler.toModel(permissaoEncontrada);
	}
}