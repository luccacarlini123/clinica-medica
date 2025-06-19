package br.com.mouzetech.clinicamedica.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;
import br.com.mouzetech.clinicamedica.domain.repository.AgendaRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.ConsultaNaoEncontradoException;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private ProfissionalService profissionalService;

	public Agenda buscarPorIdOuFalhar(Long id) {
		return this.agendaRepository.findById(id).orElseThrow(() -> new ConsultaNaoEncontradoException(id));
	}
	
	@Transactional
	public Agenda criarAgenda(Agenda agenda) {
		
		agenda.setMedico(this.profissionalService.buscarPorIdOuFalhar(agenda.getMedico().getId()));
		
		validarAntesDeAgendar(agenda);

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
	
	public List<Agenda> buscarPorMedicoEOpcionalmenteData(@PathVariable("medicoId") Long medicoId,
			@RequestParam(name = "data", required = true) LocalDate data) {

		Profissional profissional = this.profissionalService.buscarPorIdOuFalhar(medicoId);
		
		return this.agendaRepository.buscarPorMedicoEOpcionalmenteData(profissional, data);
	}

}