package br.com.mouzetech.clinicamedica.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mouzetech.clinicamedica.domain.model.Cidade;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.createTypeMap(Cidade.class, Cidade.class).addMappings(mapper -> mapper.skip(Cidade::setId));

		return modelMapper;
	}

}