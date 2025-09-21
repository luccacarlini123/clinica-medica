package br.com.mouzetech.clinicamedica.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String numero;
    
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cep;
    
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = false)
	private Cidade cidade;
	
}