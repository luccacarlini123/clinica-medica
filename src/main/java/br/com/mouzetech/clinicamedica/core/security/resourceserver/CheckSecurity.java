package br.com.mouzetech.clinicamedica.core.security.resourceserver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	@PreAuthorize("isAuthenticated() and (hasAuthority('SCOPE_READ') or hasAuthority('SCOPE_WRITE'))")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface EstaAutenticado {}
	
	@PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_WRITE')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface EstaAutenticadoParaEscrita {}
	
	@PreAuthorize("isAuthenticated() and hasAuthority('SCOPE_READ')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface EstaAutenticadoParaLeitura {}
	
	@PreAuthorize("isAuthenticated() and (hasAuthority('SCOPE_READ') or hasAuthority('SCOPE_WRITE')) "
			+ "and hasAuthority('GERENCIAR_CADASTROS_GERAIS')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface PodeGerenciarCadastros {}
	
	@PreAuthorize("isAuthenticated() and (hasAuthority('SCOPE_READ') or hasAuthority('SCOPE_WRITE')) "
			+ "and hasAuthority('GERENCIAR_PACIENTES')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface PodeGerenciarPacientes {}
	
	@PreAuthorize("isAuthenticated() and (hasAuthority('SCOPE_READ') or hasAuthority('SCOPE_WRITE')) "
			+ "and hasAuthority('GERENCIAR_AGENDAS_E_CONSULTAS')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface PodeGerenciarAgendasConsultas {}
	
	@PreAuthorize("isAuthenticated() and (hasAuthority('SCOPE_READ') or hasAuthority('SCOPE_WRITE')) "
			+ "and hasAuthority('GERENCIAR_PROFISSIONAIS')")
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD})
	public @interface PodeGerenciarProfissionais {}
}
