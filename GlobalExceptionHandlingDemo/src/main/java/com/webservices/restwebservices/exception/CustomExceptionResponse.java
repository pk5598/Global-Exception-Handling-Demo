package com.webservices.restwebservices.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



public class CustomExceptionResponse extends ResponseEntityExceptionHandler{
	private Date times;
	private String message;
	private String details;

	public  CustomExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.times = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return times;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
