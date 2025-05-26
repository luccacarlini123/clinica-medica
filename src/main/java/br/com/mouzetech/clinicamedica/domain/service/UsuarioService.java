package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mouzetech.clinicamedica.domain.model.Usuario;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarPorIdOuFalhar(Long id) {
		return this.usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

}