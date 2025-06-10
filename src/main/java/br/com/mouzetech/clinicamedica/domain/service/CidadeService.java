package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.api.model.disassembler.CidadeDisassembler;
import br.com.mouzetech.clinicamedica.domain.model.Cidade;
import br.com.mouzetech.clinicamedica.domain.model.Estado;
import br.com.mouzetech.clinicamedica.domain.repository.CidadeRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.CidadeNaoEncontradoException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeDisassembler cidadeDisassembler;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		
		Estado estado = this.estadoService.buscarPorIdOuFalhar(cidade.getEstado().getId());
		
		cidade.setEstado(estado);
		
		return this.cidadeRepository.save(cidade);
	}
	
	@Transactional
	public Cidade atualizar(Cidade cidadeNova, Long cidadeId) {
		
		cidadeNova.setEstado(this.estadoService.buscarPorIdOuFalhar(cidadeNova.getEstado().getId()));
		
		Cidade cidadeExistente = this.buscarPorIdOuFalhar(cidadeId);
		
		this.cidadeDisassembler.copyFromEntity(cidadeExistente, cidadeNova);
		
		return this.cidadeRepository.save(cidadeExistente);
	}
	
	public Cidade buscarPorIdOuFalhar(Long id) {
		return this.cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradoException(id));
	}
	
	public void excluirPorId(Long id) {
		this.cidadeRepository.delete(this.buscarPorIdOuFalhar(id));
	}
	
}