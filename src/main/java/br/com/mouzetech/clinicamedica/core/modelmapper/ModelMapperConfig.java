package br.com.mouzetech.clinicamedica.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mouzetech.clinicamedica.domain.model.Cidade;
import br.com.mouzetech.clinicamedica.domain.model.Endereco;
import br.com.mouzetech.clinicamedica.domain.model.Paciente;
import br.com.mouzetech.clinicamedica.domain.model.Profissional;
import br.com.mouzetech.clinicamedica.domain.model.Usuario;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Cidade.class, Cidade.class).addMappings(mapper -> mapper.skip(Cidade::setId));
		modelMapper.createTypeMap(Usuario.class, Usuario.class).addMappings(mapper -> mapper.skip(Usuario::setId));
		modelMapper.createTypeMap(Endereco.class, Endereco.class).addMappings(mapper -> mapper.skip(Endereco::setId));
		modelMapper.createTypeMap(Profissional.class, Profissional.class).addMappings(mapper -> mapper.skip(Profissional::setId));
		modelMapper.createTypeMap(Paciente.class, Paciente.class).addMappings(mapper -> mapper.skip(Paciente::setId));

		return modelMapper;
	}

}