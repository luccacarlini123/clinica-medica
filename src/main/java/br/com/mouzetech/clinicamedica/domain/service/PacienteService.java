package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.api.model.disassembler.PacienteDisassembler;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;
import br.com.mouzetech.clinicamedica.domain.repository.PacienteRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;
import br.com.mouzetech.clinicamedica.domain.service.exception.PacienteNaoEncontradoException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private PacienteDisassembler pacienteDisassembler;

	@Transactional
	public Paciente salvarOuAtualizar(Paciente paciente, Long idPaciente) {

		boolean jaExisteCpfCadastrado = this.pacienteRepository.existsByCpf(paciente.getCpf());
		
		if (idPaciente != null) {

			Paciente pacienteASerAtualizado = this.buscarPorIdOuFalhar(idPaciente);

			this.validarSeCpfJaExiste(paciente, pacienteASerAtualizado, jaExisteCpfCadastrado);

			this.pacienteDisassembler.copyFromEntity(pacienteASerAtualizado, paciente);

			return this.pacienteRepository.save(pacienteASerAtualizado);

		} else {

			this.validarSeCpfJaExiste(paciente, null, jaExisteCpfCadastrado);

			return this.pacienteRepository.save(paciente);
		}

	}

	private void validarSeCpfJaExiste(Paciente paciente, Paciente pacienteASerAtualizado,
			boolean jaExisteCpfCadastrado) {

		if ((pacienteASerAtualizado == null && jaExisteCpfCadastrado)
				|| (jaExisteCpfCadastrado && !pacienteASerAtualizado.getCpf().equals(paciente.getCpf()))) {
			throw new NegocioException("Já existe um paciente cadastrado com esse CPF");
		}
	}

	public Paciente buscarPorIdOuFalhar(Long id) {
		return this.pacienteRepository.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));
	}

	@Transactional
	public void excluirPorId(Long id) {
		this.pacienteRepository.delete(this.buscarPorIdOuFalhar(id));
	}
}