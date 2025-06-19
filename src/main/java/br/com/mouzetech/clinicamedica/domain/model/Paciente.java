package br.com.mouzetech.clinicamedica.domain.model;

import java.time.LocalDate;

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
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(nullable = true, name = "usuario_id")
	private Usuario usuario;
	
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	@Column(nullable = false)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoGeneroPessoa generoPessoa;

	@Column(nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(nullable = true, name = "endereco_id")
	private Endereco endereco;
}