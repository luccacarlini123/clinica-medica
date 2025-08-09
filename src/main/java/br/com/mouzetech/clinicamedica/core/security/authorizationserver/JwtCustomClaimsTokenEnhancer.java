package br.com.mouzetech.clinicamedica.core.security.authorizationserver;

import java.util.HashMap;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		if(authentication.getPrincipal() instanceof AuthUser authUser) {
			
			var customClaims = new HashMap<String, Object>();
			
			customClaims.put("nome_completo", authUser.getNome());
			customClaims.put("usuario_id", authUser.getUsuarioId());
			customClaims.put("tipoUsuario", authUser.getTipoUsuario());
			
			var defaultAccessToken = (DefaultOAuth2AccessToken) accessToken;
			defaultAccessToken.setAdditionalInformation(customClaims);
			
		}
		
		return accessToken;	
	}
}