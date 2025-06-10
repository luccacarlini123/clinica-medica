package br.com.mouzetech.clinicamedica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.mouzetech.clinicamedica.domain.service.exception.EntidadeNaoEncontradaException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERRO_DE_VALIDAÇÃO_DE_DADOS = "Ocorreu um erro de validação de dados";

	private static final String MSG_GENERICA_ERRO_USUARIO_FINAL = "Ocorreu um problema inesperado no sistema, tente novamente daqui a pouco, se o erro persistir entre em contato com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus statusHttp = HttpStatus.NOT_FOUND;

		Problem problem = this.createProblemBuilder(statusHttp, ex.getMessage(), ProblemType.ENTIDADE_NAO_ENCONTRADA)
				.build();

		return this.handleExceptionInternal(ex, problem, new HttpHeaders(), statusHttp, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Problem problemBuilder = this
				.createProblemBuilder(status, MSG_ERRO_DE_VALIDAÇÃO_DE_DADOS, ProblemType.DADOS_INVÁLIDOS)
				.fieldErrors(new ArrayList<>()).build();

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {

			String message = this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			String name = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				name = ((FieldError) objectError).getField();
			}

			problemBuilder.adicionarFieldError(name, message);

		}

		return this.handleExceptionInternal(ex, problemBuilder, headers, status, request);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		String mensagemExcecao = String.format(
				"O valor '%s' enviado para o parâmetro '%s' está inválido, "
						+ "é obrigatório informar um valor do tipo '%s'",
				ex.getValue(), ex.getName(), ex.getRequiredType().getSimpleName());

		return this
				.handleExceptionInternal(ex,
						createProblemBuilder(HttpStatus.BAD_REQUEST, mensagemExcecao, ProblemType.PARAMETRO_INVALIDO)
								.userMessage(mensagemExcecao).build(),
						new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.DADOS_INVÁLIDOS;
		
		Throwable causa = ex.getCause();
		
		if(causa instanceof InvalidFormatException) {
			return this.handleInvalidFormatException((InvalidFormatException) causa, headers, status, request, problemType);
		}
		
		Problem problemBuilder = this.createProblemBuilder(status, MSG_ERRO_DE_VALIDAÇÃO_DE_DADOS, problemType).build();
		
		return this.handleExceptionInternal(ex, problemBuilder, headers, status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request, ProblemType problemType) {
		
		String userMessage = "O valor '" + ex.getValue() + "' não é aceito pelo tipo esperado '" + ex.getTargetType().getSimpleName() + "'";
		
		Problem problem = this.createProblemBuilder(status, MSG_ERRO_DE_VALIDAÇÃO_DE_DADOS, problemType)
				.userMessage(userMessage)
				.build();
		
		return this.handleExceptionInternal(ex, problem, headers, status, request);
		
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {

			body = Problem.builder().title(ProblemType.ERRO_DE_SISTEMA.getTitle()).timeStamp(OffsetDateTime.now())
					.type(ProblemType.ERRO_DE_SISTEMA.getType()).detail(status.getReasonPhrase())
					.userMessage(MSG_GENERICA_ERRO_USUARIO_FINAL).status(status.value()).build();

		} else if (body instanceof String) {

			body = Problem.builder().title(ProblemType.ERRO_DE_SISTEMA.getType())
					.type(ProblemType.ERRO_DE_SISTEMA.getType()).timeStamp(OffsetDateTime.now()).detail((String) body)
					.userMessage(MSG_GENERICA_ERRO_USUARIO_FINAL).status(status.value()).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, String detail, ProblemType problemType) {
		return Problem.builder().status(status.value()).title(problemType.getTitle()).detail(detail).userMessage(detail)
				.type(problemType.getType()).timeStamp(OffsetDateTime.now());
	}

}