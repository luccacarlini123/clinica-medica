package br.com.mouzetech.clinicamedica.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Consulta;
import br.com.mouzetech.clinicamedica.domain.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	Optional<Pagamento> findByConsulta(Consulta consulta);
	
}