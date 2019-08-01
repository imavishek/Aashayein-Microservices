/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: EmployeeRegistrationController.java
 * @Author: Avishek Das
 * @CreatedDate: 24-06-2019
 * @Modified_By avishek.das @Last_On 24-Jun-2019 4:25:27 PM
 */

package com.aashayein.employee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.employee.command.EmployeeRegistrationCommand;
import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.DatabindingException;
import com.aashayein.employee.exception.EmployeeEmailExistsException;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.SMTPException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.service.EmployeeRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Admin/EmployeeRegistration")
@Slf4j
public class EmployeeRegistrationController {

	@Autowired
	EmployeeRegistrationService employeeRegistrationService;

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "firstName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "middleName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "lastName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "mobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateMobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "email", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateEmail", new StringTrimmerEditor(false));

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "joiningDate", new CustomDateEditor(dateFormat, false));
	}

	// Register employee and save details in database
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping(value = "/registerEmployee")
	public ResponseEntity<String> registerEmployee(
			@Valid @ModelAttribute EmployeeRegistrationCommand employeeRegistrationCommand, BindingResult result)
			throws DatabindingException, EmployeeEmailExistsException, EmployeeMobileNumberExistsException,
			SMTPException, UploadingFailedException, JsonProcessingException {

		String message = null;
		HttpStatus status = null;

		// Check data binding error
		if (result.hasErrors()) {

			List<String> messages = new ArrayList<String>();

			// Log dataBinding errors
			for (FieldError error : result.getFieldErrors()) {
				log.error("Error In DataBinding For Field: " + error.getField() + ", FieldValue: "
						+ error.getRejectedValue() + ", Message: " + error.getDefaultMessage());

				messages.add(error.getDefaultMessage());
			}

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);

		} else {

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setFirstName(employeeRegistrationCommand.getFirstName());
			employeeTo.setMiddleName(employeeRegistrationCommand.getMiddleName());
			employeeTo.setLastName(employeeRegistrationCommand.getLastName());
			employeeTo.setGender(employeeRegistrationCommand.getGender());
			employeeTo.setMobileNumber(employeeRegistrationCommand.getMobileNumber());
			employeeTo.setAlternateMobileNumber(employeeRegistrationCommand.getAlternateMobileNumber());
			employeeTo.setEmail(employeeRegistrationCommand.getEmail());
			employeeTo.setAlternateEmail(employeeRegistrationCommand.getAlternateEmail());
			employeeTo.setJobTitleId(employeeRegistrationCommand.getTitle());
			employeeTo.setRoleId(employeeRegistrationCommand.getRole());
			employeeTo.setJoiningDate(employeeRegistrationCommand.getJoiningDate());
			employeeTo.setProfilePhotoFile(employeeRegistrationCommand.getProfilePhoto());

			// Adding the employee
			EmployeeTO employee = employeeRegistrationService.addEmployee(employeeTo);

			if (employee == null) {
				status = HttpStatus.BAD_REQUEST;
				message = "Failed To Add Employee " + employeeRegistrationCommand.getFirstName() + " "
						+ employeeRegistrationCommand.getMiddleName() + " " + employeeRegistrationCommand.getLastName();
				log.error(message);
			} else {
				status = HttpStatus.OK;
				message = "Employee " + employee.getFullName() + " Added Successfully";
				log.info(message);
			}

			return new ResponseEntity<>(message, status);
		}
	}
}
