package br.com.mouzetech.clinicamedica.core.security.authorizationserver;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import br.com.mouzetech.clinicamedica.domain.model.Usuario;
import br.com.mouzetech.clinicamedica.domain.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain securityFilterChainAuthServer(HttpSecurity http) throws Exception {
		
		OAuth2AuthorizationServerConfigurer authServerConfigurer =
				new OAuth2AuthorizationServerConfigurer();
		
		authServerConfigurer.authorizationEndpoint(customizer -> customizer.consentPage("/oauth2/consent"));

		RequestMatcher requestMatcher = authServerConfigurer.getEndpointsMatcher();
		
		http.securityMatcher(requestMatcher)
		.authorizeHttpRequests(authorizeRequest ->
			authorizeRequest.anyRequest().authenticated()
		)
		.csrf(csrf -> csrf.ignoringRequestMatchers(requestMatcher))
		.formLogin(Customizer.withDefaults())
		.exceptionHandling(exceptions ->
			exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
		.apply(authServerConfigurer);
		
		return http.formLogin(customizer -> customizer.loginPage("/login")).build();
	}
	
	@Bean
	AuthorizationServerSettings authServerSettings(ClinicaMedicaSecurityProperties properties) {
		return AuthorizationServerSettings.builder().issuer(properties.getAuthServerUrl()).build();
	}

	@Bean
	RegisteredClientRepository registeredClientRepository(JdbcOperations jdbcOperations) {
		
		return new JdbcRegisteredClientRepository(jdbcOperations);
	}
	
	@Bean
	OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer(UsuarioRepository usuarioRepository) {
		return context -> {
			
			Authentication authentication = context.getPrincipal();
			
			if(authentication.getPrincipal() instanceof User user) {
				
				Usuario usuario = usuarioRepository.findByEmail(user.getUsername()).orElseThrow();
				
				Set<String> authorities = new HashSet<>();
				
				user.getAuthorities().forEach(authoritie ->
						authorities.add(authoritie.getAuthority()));
				
				context.getClaims().claim("usuario_id", usuario.getId().toString());
				
				context.getClaims().claim("authorities", authorities);
			}
		};
	}
	
	@Bean
	OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}
	
	@Bean
	JWKSource<SecurityContext> jwkSourceBean(JwtKeyStoreProperties keySetProperties) throws Exception {
		
		char[] keyStorePass = keySetProperties.getJksStorePass().toCharArray();
		
		String keyPairAlias = keySetProperties.getKeyPairAlias();
		
		Resource jksLocation = keySetProperties.getJksResource();
		
		InputStream inputStream = jksLocation.getInputStream();
		
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, keyStorePass);
		
		RSAKey rsaKey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);
		
		return new ImmutableJWKSet<>(new JWKSet(rsaKey));
	}
	
	@Bean
	OAuth2AuthorizationConsentService oAuth2AuthorizationConsentService(JdbcOperations jdbcOPerations,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationConsentService(jdbcOPerations, registeredClientRepository);
	}
	
	@Bean
	OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationQueryService(jdbcOperations, registeredClientRepository);
	}
}