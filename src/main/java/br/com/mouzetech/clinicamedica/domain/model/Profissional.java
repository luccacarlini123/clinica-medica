package br.com.mouzetech.clinicamedica.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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