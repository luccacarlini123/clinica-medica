package br.com.mouzetech.clinicamedica.domain.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Agenda;
import java.util.List;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;


@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	@Query("""
			    SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END
			    FROM Agenda a
			    WHERE a.data = :data
			      AND a.medico.id = :medicoId
			      AND (
			           :horaInicio < a.horaFim AND
			           :horaFim > a.horaInicio
			      )
			""")
	boolean existsConflitoHorario(@Param("medicoId") Long medicoId, @Param("data") LocalDate data,
			@Param("horaInicio") LocalTime horaInicio, @Param("horaFim") LocalTime horaFim);
	
	@Query("""
			FROM Agenda a
			WHERE a.medico = :medico
			  AND (:data IS NULL OR :data = a.data)
			""")
	List<Agenda> buscarPorMedicoEOpcionalmenteData(@Param("medico") Profissional medico, @Param("data") LocalDate data);
	
}