package br.com.mouzetech.clinicamedica.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;
import br.com.mouzetech.clinicamedica.domain.repository.AgendaRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.AgendaNaoEncontradaException;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private ProfissionalService profissionalService;

	public Agenda buscarPorIdOuFalhar(Long id) {
		return this.agendaRepository.findById(id).orElseThrow(() -> new AgendaNaoEncontradaException(id));
	}
	
	@Transactional
	public Agenda criarAgenda(Agenda agenda) {
		
		agenda.setMedico(this.profissionalService.buscarPorIdOuFalhar(agenda.getMedico().getId()));
		
		this.validarAntesDeAgendar(agenda);

		return agendaRepository.save(agenda);
	}

	private void validarAntesDeAgendar(Agenda agenda) {
		
		if(agenda.getHoraInicio().equals(agenda.getHoraFim())) {
			throw new NegocioException("Os horários de início e fim não podem ser iguais");
		}
		
		if(agenda.getHoraInicio().isAfter(agenda.getHoraFim())) {
			throw new NegocioException("O horário de início deve ser menor que o horário do fim");
		}
		
		boolean existeConflito = this.agendaRepository.existsConflitoHorario(agenda.getMedico().getId(),
				agenda.getData(), agenda.getHoraInicio(), agenda.getHoraFim());

		if (existeConflito) {
			throw new NegocioException("Já existe um agendamento para este horário.");
		}
	}
	
	public List<Agenda> buscarPorMedicoEOpcionalmenteData(Long medicoId, LocalDate dataInicio, LocalDate dataFim) {

		this.validarDatasParaPesquisa(dataInicio, dataFim);
		
		Profissional profissional = this.profissionalService.buscarPorIdOuFalhar(medicoId);
		
		return this.agendaRepository.buscarPorMedicoEOpcionalmenteData(profissional.getId(), dataInicio, dataFim);
	}

	private void validarDatasParaPesquisa(LocalDate dataInicio, LocalDate dataFim) {
		if(dataInicio == null || dataFim == null) {
			throw new NegocioException("Para buscar por data é obrigatório informar a data de início e a data fim");
		}
		
		if(dataInicio.isAfter(dataFim)) {
			throw new NegocioException("A data de início deve ser menor ou igual a data fim");
		}
	}

}