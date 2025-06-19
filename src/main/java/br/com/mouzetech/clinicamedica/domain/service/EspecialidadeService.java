package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Especialidade;
import br.com.mouzetech.clinicamedica.domain.repository.EspecialidadeRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.EspecialidadeNaoEncontradaException;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@Transactional
	public Especialidade salvar(Especialidade especialidade) {
		return this.especialidadeRepository.save(especialidade);
	}
	
	public Especialidade buscarPorIdOuFalhar(Long id) {
		return this.especialidadeRepository.findById(id).orElseThrow(() -> new EspecialidadeNaoEncontradaException(id));
	}

	@Transactional
	public void excluirPorId(Long id) {
		this.especialidadeRepository.delete(this.buscarPorIdOuFalhar(id));
	}
}