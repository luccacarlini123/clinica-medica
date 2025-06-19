package br.com.mouzetech.clinicamedica.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import br.com.mouzetech.clinicamedica.domain.model.Consulta;



@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	Optional<Consulta> findByAgenda(Agenda agenda);	
}