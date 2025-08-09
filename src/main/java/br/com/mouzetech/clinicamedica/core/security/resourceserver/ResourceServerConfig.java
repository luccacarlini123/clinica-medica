package br.com.mouzetech.clinicamedica.core.security.resourceserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationEntrypoint customAuthenticationEntrypoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		HttpSecurity httpSecurity = http.oauth2ResourceServer(
						oauth2 -> oauth2.authenticationEntryPoint(customAuthenticationEntrypoint).jwt()
						.jwtAuthenticationConverter(this.jwtAuthenticationConverter()));

		httpSecurity.cors();
	}
	
	private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
		var jwtAuthConverter = new JwtAuthenticationConverter();
		
		jwtAuthConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if(authorities == null) {
				authorities = new ArrayList<>();
			}
			
			var scopesJwtAuthorities = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = scopesJwtAuthorities.convert(jwt);
			
			grantedAuthorities.addAll(authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
			
			return grantedAuthorities;
			
		});
		
		return jwtAuthConverter;
	}
	
//	@Bean
//	JwtDecoder jwtDecoder() {
//		SecretKeySpec secretKey = new SecretKeySpec("mouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-security".getBytes(), "HmacSHA256");
//		
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}