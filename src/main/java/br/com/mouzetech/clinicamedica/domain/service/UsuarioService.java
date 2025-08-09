package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.api.model.disassembler.UsuarioDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.AtualizarSenhaInput;
import br.com.mouzetech.clinicamedica.api.model.input.UsuarioAtualizarInput;
import br.com.mouzetech.clinicamedica.domain.model.Usuario;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;
import br.com.mouzetech.clinicamedica.domain.service.exception.UsuarioNaoEncontradoException;

@Service
public class UsuarioService {

	private static final String MSG_ERRO_EMAIL_EXISTENTE = "Já existe um usuário cadastrado com esse email";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioDisassembler usuarioDisassembler;

	public Usuario buscarPorIdOuFalhar(Long id) {
		return this.usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {

		if (this.usuarioRepository.existsByEmail(usuario.getEmail())) {
			throw new NegocioException(MSG_ERRO_EMAIL_EXISTENTE);
		}

		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));

		return this.usuarioRepository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(UsuarioAtualizarInput usuarioInput, Long idUsuario) {

		Usuario usuarioEncontrado = this.buscarPorIdOuFalhar(idUsuario);

		if (this.usuarioRepository.existsByEmail(usuarioInput.getEmail())
				&& !usuarioInput.getEmail().equals(usuarioEncontrado.getEmail())) {
			throw new NegocioException(MSG_ERRO_EMAIL_EXISTENTE);
		}

		this.usuarioDisassembler.copyFromInputAtualizar(usuarioEncontrado, usuarioInput);

		return this.usuarioRepository.save(usuarioEncontrado);
	}

	@Transactional
	public void atualizarSenha(Long usuarioId, AtualizarSenhaInput atualizarSenhaInput) {

		Usuario usuarioEncontrado = this.buscarPorIdOuFalhar(usuarioId);

		if (!this.passwordEncoder.matches(atualizarSenhaInput.getSenhaAtual(), usuarioEncontrado.getSenha())) {
			throw new NegocioException("A senha atual informada não é igual a senha cadastrada");
		}

		usuarioEncontrado.setSenha(this.passwordEncoder.encode(atualizarSenhaInput.getSenhaNova()));

		this.usuarioRepository.save(usuarioEncontrado);
	}
}