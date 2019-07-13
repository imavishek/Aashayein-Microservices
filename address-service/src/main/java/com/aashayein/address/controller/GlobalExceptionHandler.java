/**
 * @ProjectName: address-service
 * @PackageName: com.aashayein.address.controller
 * @FileName: GlobalExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 12:15:46 AM
 */

package com.aashayein.address.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.address.dto.ErrorResponse;
import com.aashayein.address.exception.DatabindingException;

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
