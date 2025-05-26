package br.com.mouzetech.clinicamedica.api.exceptionhandler;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Builder
@JsonInclude(value = Include.NON_NULL)
@Getter
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	private OffsetDateTime timeStamp;
	private String userMessage;
	
}