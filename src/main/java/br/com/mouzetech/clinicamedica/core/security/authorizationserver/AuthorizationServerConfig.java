package br.com.mouzetech.clinicamedica.core.security.authorizationserver;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("clinica-medica-web").secret(this.passwordEncoder.encode("123"))
				.scopes("READ", "WRITE").authorizedGrantTypes("password", "authorization_code", "refresh_token")
				.accessTokenValiditySeconds(60 * 60 * 24 * 5).refreshTokenValiditySeconds(60 * 60 * 24 * 10)
				.redirectUris("http://localhost:8089");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		var enhancerTokenChain = new TokenEnhancerChain();
		enhancerTokenChain
				.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));

		endpoints.reuseRefreshTokens(false).userDetailsService(this.userDetailsService)
				.authenticationManager(this.authenticationManager).accessTokenConverter(jwtAccessTokenConverter())
				.tokenEnhancer(enhancerTokenChain);
	}

	@Bean
	JWKSet jwkSet() {
		RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) getKeyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
				.algorithm(JWSAlgorithm.RS256).keyID("clinicamedica-api-keyID");

		return new JWKSet(builder.build());
	}

	@Bean
	JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

//		utilizado para assinar chave simétrica
//		jwtAccessTokenConverter.setSigningKey("mouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-securitymouzetech-security");

		jwtAccessTokenConverter.setKeyPair(getKeyPair());

		return jwtAccessTokenConverter;
	}

	private KeyPair getKeyPair() {
		return new KeyStoreKeyFactory(this.jwtKeyStoreProperties.getJksResource(),
				this.jwtKeyStoreProperties.getJksStorePass().toCharArray())
				.getKeyPair(this.jwtKeyStoreProperties.getKeyPairAlias());
	}
}