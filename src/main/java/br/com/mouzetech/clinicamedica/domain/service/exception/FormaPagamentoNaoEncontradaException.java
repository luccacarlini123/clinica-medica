package br.com.mouzetech.clinicamedica.domain.service.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 5447589385546952571L;

	public FormaPagamentoNaoEncontradaException(Long idFormaPagamento) {
		super("Não foi encontrada uma forma de pagamento com o id: " + idFormaPagamento);
	}
	
}