package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Estado;
import br.com.mouzetech.clinicamedica.domain.repository.EstadoRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.EstadoNaoEncontradoException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Transactional
	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}
	
	public Estado buscarPorIdOuFalhar(Long id) {
		return this.estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}

	@Transactional
	public void excluirPorId(Long id) {
		this.estadoRepository.delete(this.buscarPorIdOuFalhar(id));
	}
}