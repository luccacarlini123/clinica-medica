package br.com.mouzetech.clinicamedica.core.config.security;
import java.io.IOException;
import java.time.OffsetDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mouzetech.clinicamedica.api.exceptionhandler.Problem;
import br.com.mouzetech.clinicamedica.api.exceptionhandler.ProblemType;

@Configuration
public class CustomAuthenticationEntrypoint implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");	
		
		Problem.ProblemBuilder problemBuilder = Problem.builder();
		problemBuilder.title("Sem autorização");
		problemBuilder.status(response.getStatus());
		problemBuilder.timeStamp(OffsetDateTime.now());
		problemBuilder.detail("Sem autorização para acessar o recurso");
		problemBuilder.userMessage("Sem autorização para acessar o recurso");
		problemBuilder.type(ProblemType.SEM_AUTORIZACAO.getType());
		
		response.getWriter().write(this.objectMapper.writeValueAsString(problemBuilder.build()));
	}
}