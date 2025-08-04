package br.com.mouzetech.clinicamedica.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.api.model.disassembler.ConsultaDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.ConsultaInput;
import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import br.com.mouzetech.clinicamedica.domain.model.Consulta;
import br.com.mouzetech.clinicamedica.domain.model.FormaPagamento;
import br.com.mouzetech.clinicamedica.domain.model.Pagamento;
import br.com.mouzetech.clinicamedica.domain.model.TipoStatusConsulta;
import br.com.mouzetech.clinicamedica.domain.model.TipoStatusPagamento;
import br.com.mouzetech.clinicamedica.domain.repository.ConsultaRepository;
import br.com.mouzetech.clinicamedica.domain.repository.PagamentoRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.ConsultaNaoEncontradaException;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private ConsultaDisassembler consultaDisassembler;

	public Consulta buscarPorIdOuFalhar(Long idConsulta) {
		return this.consultaRepository.findById(idConsulta)
				.orElseThrow(() -> new ConsultaNaoEncontradaException(idConsulta));
	}

	@Transactional
	public Consulta agendar(ConsultaInput consultaInput) {

		Consulta consulta = this.consultaDisassembler.toEntityFromInput(consultaInput);

		Agenda agenda = this.agendaService.buscarPorIdOuFalhar(consulta.getAgenda().getId());

		this.validarSeJaExisteConsultaAgendada(agenda);

		consulta.setAgenda(agenda);
		consulta.setPaciente(this.pacienteService.buscarPorIdOuFalhar(consulta.getPaciente().getId()));
		consulta.setStatus(TipoStatusConsulta.AGENDADA);

		consulta = this.consultaRepository.save(consulta);

		this.criarPagamentoParaConsulta(consultaInput, consulta);

		return consulta;
	}

	private void validarSeJaExisteConsultaAgendada(Agenda agenda) {

		if (this.consultaRepository.findByAgenda(agenda).isPresent()) {
			throw new NegocioException("Já existe uma consulta agendada com este médico nesse horário");
		}
	}

	private void criarPagamentoParaConsulta(ConsultaInput consultaInput, Consulta consulta) {

		FormaPagamento formaPagamento = this.formaPagamentoService
				.buscarPorIdOuFalhar(consultaInput.getFormaPagamento().getId());

		Pagamento pagamento = Pagamento.builder().consulta(consulta).formaPagamento(formaPagamento)
				.status(TipoStatusPagamento.PENDENTE).valor(consultaInput.getValor()).build();

		this.pagamentoRepository.save(pagamento);
	}

	@Transactional
	public void desmarcarConsulta(Long consultaId) {

		Consulta consulta = this.buscarPorIdOuFalhar(consultaId);

		this.validarSePossivelDesmarcar(consulta);

		consulta.desmarcar();

		Optional<Pagamento> pagamento = this.pagamentoRepository.findByConsulta(consulta);

		if (pagamento.isPresent()) {
			pagamento.get().cancelar();
		}
	}

	private void validarSePossivelDesmarcar(Consulta consulta) {
		if (consulta.jaFoiRealizada()) {
			throw new NegocioException("A consulta já foi realizada, impossível desmarcar");
		}

		if (consulta.jaFoiDesmarcada()) {
			throw new NegocioException("A consulta já foi desmarcada");
		}
	}

	@Transactional
	public void finalizarConsulta(Long idConsulta) {

		Consulta consulta = this.buscarPorIdOuFalhar(idConsulta);

		Optional<Pagamento> pagamento = this.pagamentoRepository.findByConsulta(consulta);

		this.validarSePossivelFinalizar(pagamento, consulta);

		consulta.finalizar();
	}

	private void validarSePossivelFinalizar(Optional<Pagamento> pagamento, Consulta consulta) {

		if (!TipoStatusConsulta.AGENDADA.equals(consulta.getStatus())) {
			throw new NegocioException("Só é possível finalizar uma consulta AGENDADA");
		}

		if (pagamento.isEmpty() || (!TipoStatusPagamento.REALIZADO.equals(pagamento.get().getStatus())
				|| pagamento.get().getDataPagamento() == null)) {

			throw new NegocioException("A consulta não está paga, impossível finalizar");
		}
	}

	@Transactional
	public void pagarConsulta(Long idConsulta) {

		Consulta consulta = this.buscarPorIdOuFalhar(idConsulta);

		Pagamento pagamento = this.pagamentoRepository.findByConsulta(consulta)
				.orElseThrow(() -> new NegocioException("Não foi encontrado um pagamento para esta consulta"));

		if (!TipoStatusPagamento.PENDENTE.equals(pagamento.getStatus())) {
			throw new NegocioException("É preciso que o pagamento da consulta esteja como PENDENTE para ser realizado");
		}
		
		if (!TipoStatusConsulta.AGENDADA.equals(consulta.getStatus())) {
			throw new NegocioException("Só é possível realizar o pagamento de uma consulta AGENDADA");
		}

		pagamento.pagar();
	}
}