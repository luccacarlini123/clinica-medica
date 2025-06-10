package br.com.mouzetech.clinicamedica.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Cidade;
import br.com.mouzetech.clinicamedica.domain.model.Estado;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	List<Cidade> findByEstado(Estado estado);
	
}