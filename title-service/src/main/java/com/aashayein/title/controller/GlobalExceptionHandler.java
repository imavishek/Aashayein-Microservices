/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.controller
 * @FileName: GlobalExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:20:54 PM
 */

package com.aashayein.title.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.title.dto.ErrorResponse;
import com.aashayein.title.exception.DatabindingException;
import com.aashayein.title.exception.EmployeeTitleNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/* Handler Not Found Exception Handler */
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(Exception e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add(
				"The page you are looking for might have been removed had its name changed or is temporarily unavailable.");

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError("Not Found");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/* Method Not Supported Exception Handler */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add(e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		errorResponse.setError("Method Not Allowed");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/* Databinding Exception Handler */
	@ExceptionHandler({ DatabindingException.class })
	public ResponseEntity<ErrorResponse> handleDatabindingException(DatabindingException e,
			HttpServletRequest request) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(e.getErrors());
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* Employee Title Not Found Exception Handler */
	@ExceptionHandler({ EmployeeTitleNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleEmployeeTitleNotFoundException(EmployeeTitleNotFoundException e,
			HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("Employee Title Not Found: " + e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError("Not Found");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/* Generic Exception Handler */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("The server has encountered an unexpected error. Please contact administrator.");

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setError("Internal Server Error");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
