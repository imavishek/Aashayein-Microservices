/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: GlobalExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 17-06-2019
 * @Modified_By avishek.das @Last_On 17-Jun-2019 5:05:55 PM
 */

package com.aashayein.employee.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.employee.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/*
	 * Handler Not Found Exception Handler
	 */
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
}
