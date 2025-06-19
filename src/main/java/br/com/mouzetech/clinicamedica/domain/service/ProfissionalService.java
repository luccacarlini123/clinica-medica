package br.com.mouzetech.clinicamedica.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mouzetech.clinicamedica.api.model.disassembler.ProfissionalDisassembler;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;
import br.com.mouzetech.clinicamedica.domain.repository.ProfissionalRepository;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;
import br.com.mouzetech.clinicamedica.domain.service.exception.NegocioException;
import br.com.mouzetech.clinicamedica.domain.service.exception.ProfissionalNaoEncontradaException;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EspecialidadeService especialidadeService;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private ProfissionalDisassembler profissionalDisassembler;

	@Transactional
	public Profissional salvarOuAtualizar(Profissional profissional, Long profissionalId) {

		profissional.setEspecialidade(
				this.especialidadeService.buscarPorIdOuFalhar(profissional.getEspecialidade().getId()));

		profissional.getEndereco()
				.setCidade(this.cidadeService.buscarPorIdOuFalhar(profissional.getEndereco().getCidade().getId()));

		if (profissionalId != null) {

			Profissional profissionalASerAtualizado = this.buscarPorIdOuFalhar(profissionalId);

			this.validarExistenciaDeEmailOuCrm(profissional, profissionalASerAtualizado, false);

			this.profissionalDisassembler.copyFromEntity(profissionalASerAtualizado, profissional);

			profissionalASerAtualizado.getEndereco().setCidade(profissional.getEndereco().getCidade());
			
			return this.profissionalRepository.save(profissionalASerAtualizado);
			
		} else {

			this.validarExistenciaDeEmailOuCrm(profissional, null, true);
			
			return this.profissionalRepository.save(profissional);
		}
	}

	private void validarExistenciaDeEmailOuCrm(Profissional profissionalAtualizado,
			Profissional profissionalASerAtualizado, boolean estaInserindo) {

		boolean existeEmail = this.usuarioRepository.existsByEmail(profissionalAtualizado.getUsuario().getEmail());

		if ((existeEmail && estaInserindo)
				|| (existeEmail && profissionalASerAtualizado != null && !profissionalASerAtualizado.getUsuario()
						.getEmail().equals(profissionalAtualizado.getUsuario().getEmail()))) {
			throw new NegocioException("O email informado já está sendo utilizado");
		}

		boolean existeCrm = this.profissionalRepository.existsByCrm(profissionalAtualizado.getCrm());

		if ((existeCrm && estaInserindo)
				|| (existeCrm && profissionalASerAtualizado != null && profissionalASerAtualizado.getCrm() != null
						&& !profissionalASerAtualizado.getCrm().equals(profissionalAtualizado.getCrm()))) {
			throw new NegocioException("O CRM informado já está sendo utilizado");
		}
	}

	public Profissional buscarPorIdOuFalhar(Long id) {
		return this.profissionalRepository.findById(id).orElseThrow(() -> new ProfissionalNaoEncontradaException(id));
	}

	@Transactional
	public void excluirPorId(Long profissionalId) {
		this.profissionalRepository.delete(this.buscarPorIdOuFalhar(profissionalId));
	}
}