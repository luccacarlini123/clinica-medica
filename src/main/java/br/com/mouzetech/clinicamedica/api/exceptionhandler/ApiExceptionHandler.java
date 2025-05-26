package br.com.mouzetech.clinicamedica.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mouzetech.clinicamedica.domain.service.exception.EntidadeNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_GENERICA_ERRO_USUARIO_FINAL = "Ocorreu um problema inesperado no sistema, tente novamente daqui a pouco, se o erro persistir entre em contato com o administrador do sistema.";

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus statusHttp = HttpStatus.NOT_FOUND;

		Problem problem = this.createProblemBuilder(statusHttp, ex.getMessage(), ProblemType.ENTIDADE_NAO_ENCONTRADA)
				.build();

		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), statusHttp, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problem.builder().timeStamp(OffsetDateTime.now()).detail(status.getReasonPhrase())
					.userMessage(MSG_GENERICA_ERRO_USUARIO_FINAL).build();
		} else if (body instanceof String) {
			body = Problem.builder().timeStamp(OffsetDateTime.now()).detail((String) body)
					.userMessage(MSG_GENERICA_ERRO_USUARIO_FINAL).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, String detail, ProblemType problemType) {
		return Problem.builder().status(status.value()).title(problemType.getTitle()).detail(detail).userMessage(detail)
				.type(problemType.getType()).timeStamp(OffsetDateTime.now());
	}

}