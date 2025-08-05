package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mouzetech.clinicamedica.domain.model.Permissao;
import br.com.mouzetech.clinicamedica.domain.repository.PermissaoRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.PermissaoNaoEncontradaException;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;

	public Permissao buscarPorIdOuFalhar(Long id) {
		return this.permissaoRepository.findById(id).orElseThrow(() -> new PermissaoNaoEncontradaException(id));
	}

}