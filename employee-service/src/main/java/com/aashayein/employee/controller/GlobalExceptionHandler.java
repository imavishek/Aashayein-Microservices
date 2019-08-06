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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aashayein.employee.dto.ErrorResponse;
import com.aashayein.employee.exception.BadRequestException;
import com.aashayein.employee.exception.DatabindingException;
import com.aashayein.employee.exception.EmployeeEmailExistsException;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.EmployeeNotFoundException;
import com.aashayein.employee.exception.InvalidTokenException;
import com.aashayein.employee.exception.SMTPException;
import com.aashayein.employee.exception.UsernameNotFoundException;

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

	/* Missing Servlet Request Parameter Exception Handler */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add(e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* Username Not Found Exception Handler */
	@ExceptionHandler({ UsernameNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e,
			HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add(e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setError("Not Found");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/* Bad Request Exception Handler */
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add(e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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

	/* Employee Email Exists Exception Handler */
	@ExceptionHandler({ EmployeeEmailExistsException.class })
	public ResponseEntity<ErrorResponse> handleEmployeeEmailExistsException(EmployeeEmailExistsException e,
			HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("Email " + e.getMessage() + " already exists.");

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* Employee MobileNumber Exists Exception Handler */
	@ExceptionHandler({ EmployeeMobileNumberExistsException.class })
	public ResponseEntity<ErrorResponse> handleEmployeeMobileNumberExistsException(
			EmployeeMobileNumberExistsException e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("MobileNumber " + e.getMessage() + " already exists.");

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* SMTP Exception Handler */
	@ExceptionHandler({ SMTPException.class })
	public ResponseEntity<ErrorResponse> handleSMTPException(SMTPException e, HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("SMTP verification failed for mailId: " + e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* Invalid Token Exception Handler */
	@ExceptionHandler({ InvalidTokenException.class })
	public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e,
			HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("Invalid Token: " + e.getMessage());

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError("Bad Request");
		errorResponse.setMessage(messages);
		errorResponse.setPath(request.getRequestURI());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/* Employee Not Found Exception Handler */
	@ExceptionHandler({ EmployeeNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException e,
			HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();
		messages.add("Employee Not Found: " + e.getMessage());

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
