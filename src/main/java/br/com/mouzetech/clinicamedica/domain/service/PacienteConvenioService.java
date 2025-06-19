package br.com.mouzetech.clinicamedica.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mouzetech.clinicamedica.api.model.disassembler.PacienteConvenioDisassembler;
import br.com.mouzetech.clinicamedica.api.model.input.PacienteConvenioInput;
import br.com.mouzetech.clinicamedica.domain.model.Convenio;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;
import br.com.mouzetech.clinicamedica.domain.model.PacienteConvenio;
import br.com.mouzetech.clinicamedica.domain.repository.PacienteConvenioRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;

@Service
public class PacienteConvenioService {

	@Autowired
	private PacienteConvenioRepository pacienteConvenioRepository;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private ConvenioService convenioService;

	@Autowired
	private PacienteConvenioDisassembler pacienteConvenioDisassembler;

	public PacienteConvenio salvar(PacienteConvenioInput pacienteConvenioInput) {

		Paciente paciente = this.pacienteService.buscarPorIdOuFalhar(pacienteConvenioInput.getPacienteId());
		Convenio convenio = this.convenioService.buscarPorIdOuFalhar(pacienteConvenioInput.getConvenioId());

		if (this.pacienteConvenioRepository.existsByPacienteAndConvenio(paciente, convenio)) {
			throw new NegocioException("Esse paciente já está associado a este convênio");
		}

		PacienteConvenio pacienteConvenio = this.pacienteConvenioDisassembler.toEntityFromInput(pacienteConvenioInput);
		pacienteConvenio.setPaciente(paciente);
		pacienteConvenio.setConvenio(convenio);
		
		return this.pacienteConvenioRepository.save(pacienteConvenio);
	}
	
	public List<PacienteConvenio> buscarConveniosDoPaciente(Long pacienteId) {
		
		Paciente paciente = this.pacienteService.buscarPorIdOuFalhar(pacienteId);
		
		return this.pacienteConvenioRepository.findByPaciente(paciente);
	}
}