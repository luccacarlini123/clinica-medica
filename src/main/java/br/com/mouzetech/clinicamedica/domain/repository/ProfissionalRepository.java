package br.com.mouzetech.clinicamedica.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mouzetech.clinicamedica.domain.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	boolean existsByCrm(String crm);
	
	@Query("from Profissional p join fetch p.usuario join fetch p.especialidade join fetch p.endereco join fetch p.endereco.cidade join fetch p.endereco.cidade.estado")
	List<Profissional> findAll();
	
	@Query("""
			from Profissional p
			join fetch p.usuario
			join fetch p.especialidade
			join fetch p.endereco
			join fetch p.endereco.cidade
			join fetch p.endereco.cidade.estado
			where p.id = :pedidoId
			""")
	Optional<Profissional> findById(@Param("pedidoId") Long pedidoId);
}