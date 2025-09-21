package br.com.mouzetech.clinicamedica.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profissional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
	@ManyToOne
	@JoinColumn(name = "especialidade_id", nullable = false)
    private Especialidade especialidade;
    
	@Column(nullable = true)
    private String crm;
    
    @Enumerated(EnumType.STRING)
    private TipoGeneroPessoa generoPessoa;
    
    @Column(name = "convenio_participa")
    private boolean convenioParticipa;
    
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;	
}