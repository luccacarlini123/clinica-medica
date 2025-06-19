package br.com.mouzetech.clinicamedica.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Convenio;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;
import br.com.mouzetech.clinicamedica.domain.model.PacienteConvenio;
import br.com.mouzetech.clinicamedica.domain.model.PacienteConvenioKey;
import java.util.List;



@Repository
public interface PacienteConvenioRepository extends JpaRepository<PacienteConvenio, PacienteConvenioKey> {

	boolean existsByPacienteAndConvenio(Paciente paciente, Convenio convenio);
	
	List<PacienteConvenio> findByPaciente(Paciente paciente);
	
}