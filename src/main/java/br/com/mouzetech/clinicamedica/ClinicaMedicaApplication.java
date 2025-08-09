package br.com.mouzetech.clinicamedica;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mouzetech.clinicamedica.core.security.authorizationserver.Base64ProtocolResolver;

@SpringBootApplication
public class ClinicaMedicaApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		
		var app = new SpringApplication(ClinicaMedicaApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);

		SpringApplication.run(ClinicaMedicaApplication.class, args);
	}

}