/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.aashayein.zuul.controller
 * @FileName: GlobalExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 18-06-2019
 * @Modified_By avishek.das @Last_On 18-Jun-2019 11:31:26 PM
 */

package com.aashayein.zuul.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.zuul.controller.GlobalExceptionHandler;
import com.aashayein.zuul.dto.ErrorResponse;

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
