package br.com.mouzetech.clinicamedica.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mouzetech.clinicamedica.domain.model.Usuario;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;
import br.com.mouzetech.clinicamedica.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> buscarTodos() {
		return this.usuarioRepository.findAll();
	}

	@GetMapping("/{usuarioId}")
	public Usuario buscarPorId(@PathVariable("usuarioId") Long usuarioId) {
		return this.usuarioService.buscarPorIdOuFalhar(usuarioId);
	}
}