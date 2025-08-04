package br.com.mouzetech.clinicamedica.core.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		HttpSecurity httpSecurity = http.authorizeRequests(auth -> auth.anyRequest().authenticated())
				.oauth2ResourceServer(
						oauth2 -> oauth2.authenticationEntryPoint(customAuthenticationEntrypoint).jwt());

		httpSecurity.cors();
	}
	
//	@Bean
//	JwtDecoder jwtDecoder() {
//		SecretKeySpec secretKey = new SecretKeySpec("mouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-security".getBytes(), "HmacSHA256");
//		
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
}