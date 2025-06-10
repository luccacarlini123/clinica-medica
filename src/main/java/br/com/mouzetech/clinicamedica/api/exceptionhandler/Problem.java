package br.com.mouzetech.clinicamedica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	private OffsetDateTime timeStamp;
	private String userMessage;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<FieldError> fieldErrors; 
	
	public void adicionarFieldError(String fieldName, String errorMessage) {
		FieldError field = new FieldError(fieldName, errorMessage);
		
		if(fieldErrors == null) {
			this.fieldErrors = new ArrayList<>();
		}
		
		this.fieldErrors.add(field);
	}
	
	@AllArgsConstructor
	@Getter
	@Setter
	class FieldError {
		private String fieldName;
		private String errorMessage;
	}
	
}