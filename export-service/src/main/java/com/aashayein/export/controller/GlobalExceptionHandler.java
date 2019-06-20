/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.controller
 * @FileName: GlobalExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 1:11:43 AM
 */

package com.aashayein.export.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.export.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/* Handler Not Found Exception Handler */
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ResponseEntity<ErrorResponse> handlerNoHandlerFoundException(Exception e, HttpServletRequest request) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError("Not Found");
		errorResponse.setMessage(
				"The page you are looking for might have been removed had its name changed or is temporarily unavailable.");
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/* RestTemplate whenever it encounters client-side HTTP errors. */
	@ExceptionHandler({ RestClientException.class })
	public ResponseEntity<ErrorResponse> handlerInvalidURI(Exception e, HttpServletRequest request) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError("Not Found");
		errorResponse.setMessage(
				"RestTemplate Error : The page you are looking for might have been removed had its name changed or is temporarily unavailable.");
		errorResponse.setPath(request.getRequestURI());

		log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/* Generic Exception Handler */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setError("Internal Server Error");
		errorResponse.setMessage("The server has encountered an unexpected error. Please contact administrator.");
		errorResponse.setPath(request.getRequestURI());

		log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
