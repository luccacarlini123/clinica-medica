package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.FormaPagamento;
import br.com.mouzetech.clinicamedica.domain.repository.FormaPagamentoRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.FormaPagamentoNaoEncontradaException;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return this.formaPagamentoRepository.save(formaPagamento);
	}

	public FormaPagamento buscarPorIdOuFalhar(Long id) {
		return this.formaPagamentoRepository.findById(id)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
	}

	@Transactional
	public void excluirPorId(Long id) {
		this.formaPagamentoRepository.delete(this.buscarPorIdOuFalhar(id));
	}
}