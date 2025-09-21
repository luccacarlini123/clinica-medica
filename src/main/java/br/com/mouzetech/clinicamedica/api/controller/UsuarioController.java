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

import br.com.mouzetech.clinicamedica.api.model.assembler.UsuarioAssembler;
import br.com.mouzetech.clinicamedica.api.model.disassembler.UsuarioDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.AtualizarSenhaInput;
import br.com.mouzetech.clinicamedica.api.model.input.UsuarioAtualizarInput;
import br.com.mouzetech.clinicamedica.api.model.input.UsuarioInput;
import br.com.mouzetech.clinicamedica.api.model.representation.UsuarioModel;
import br.com.mouzetech.clinicamedica.core.security.resourceserver.CheckSecurity;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;
import br.com.mouzetech.clinicamedica.domain.service.UsuarioService;
import jakarta.validation.Valid;

@CheckSecurity.PodeGerenciarCadastros
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioAssembler usuarioAssembler;

	@Autowired
	private UsuarioDisassembler usuarioDisassembler;

	@GetMapping
	public List<UsuarioModel> buscarTodos() {
		return this.usuarioAssembler.toCollectionModel(this.usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public UsuarioModel buscarPorId(@PathVariable Long id) {
		return this.usuarioAssembler.toModel(this.usuarioService.buscarPorIdOuFalhar(id));
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		this.usuarioRepository.delete(this.usuarioService.buscarPorIdOuFalhar(id));
	}

	@PostMapping
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput) {

		return this.usuarioAssembler
				.toModel(this.usuarioService.salvar(this.usuarioDisassembler.toEntityFromInput(usuarioInput)));
	}

	@PutMapping("/{id}")
	public UsuarioModel atualizar(@RequestBody @Valid UsuarioAtualizarInput usuarioInput, @PathVariable Long id) {

		return this.usuarioAssembler.toModel(this.usuarioService.atualizar(usuarioInput, id));
	}
	
	@PutMapping("/{id}/senha")
	public void atualizarSenha(@RequestBody @Valid AtualizarSenhaInput atualizarSenhaInput, @PathVariable Long id) {

		this.usuarioService.atualizarSenha(id, atualizarSenhaInput);
	}
}