package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Convenio;
import br.com.mouzetech.clinicamedica.domain.repository.ConvenioRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.ConvenioNaoEncontradoException;

@Service
public class ConvenioService {

	@Autowired
	private ConvenioRepository convenioRepository;

	@Transactional
	public Convenio salvar(Convenio convenio) {
		return this.convenioRepository.save(convenio);
	}

	public Convenio buscarPorIdOuFalhar(Long id) {
		return this.convenioRepository.findById(id).orElseThrow(() -> new ConvenioNaoEncontradoException(id));
	}

	@Transactional
	public void excluirPorId(Long id) {
		this.convenioRepository.delete(this.buscarPorIdOuFalhar(id));
	}
}