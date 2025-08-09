package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Grupo;
import br.com.mouzetech.clinicamedica.domain.repository.GrupoRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.GrupoNaoEncontradoException;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private PermissaoService permissaoService;

	public Grupo buscarPorIdOuFalhar(Long id) {
		return this.grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		this.buscarPorIdOuFalhar(grupoId).associarPermissao(this.permissaoService.buscarPorIdOuFalhar(permissaoId));
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		this.buscarPorIdOuFalhar(grupoId).desassociarPermissao(this.permissaoService.buscarPorIdOuFalhar(permissaoId));
	}

}