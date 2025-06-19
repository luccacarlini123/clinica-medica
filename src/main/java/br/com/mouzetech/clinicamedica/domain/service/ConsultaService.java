package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import br.com.mouzetech.clinicamedica.domain.model.Consulta;
import br.com.mouzetech.clinicamedica.domain.repository.ConsultaRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Transactional
	public Consulta agendar(Consulta consulta) {
		
		Agenda agenda = this.agendaService.buscarPorIdOuFalhar(consulta.getAgenda().getId());
		
		if(this.consultaRepository.findByAgenda(agenda).isPresent()) {
			throw new NegocioException("Essa agenda já está associada a outra consulta");
		}
		
		consulta.setAgenda(agenda);
		consulta.setPaciente(this.pacienteService.buscarPorIdOuFalhar(consulta.getPaciente().getId()));
		
		return this.consultaRepository.save(consulta);
	}
}