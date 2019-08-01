/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: EmployeeController.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 12:13:49 PM
 */

package com.aashayein.employee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.employee.command.EditEmployeeCommand;
import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.DatabindingException;
import com.aashayein.employee.exception.EmployeeNotFoundException;
import com.aashayein.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Admin/Employee")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "joiningDate", new CustomDateEditor(dateFormat, false));
	}

	// Get all employee details
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@GetMapping(value = "/getEmployees")
	public List<EmployeeTO> getEmployees() {

		List<EmployeeTO> employees = employeeService.getAllEmployees();

		return employees;
	}

	// Get employee by employeeId
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@GetMapping(value = "/getEmployeeById/{id}")
	public EmployeeTO getEmployeeById(@PathVariable("id") String id)
			throws DatabindingException, EmployeeNotFoundException {

		EmployeeTO employee = null;
		Integer employeeId = null;
		List<String> messages = new ArrayList<String>();

		try {
			employeeId = Integer.parseInt(id);
			employee = employeeService.getEmployeeById(employeeId);

			if (employee == null) {
				throw new EmployeeNotFoundException(employeeId.toString());
			}
		} catch (NumberFormatException e) {
			log.error("Invalid Employee Id");
			messages.add("Invalid Employee Id");

			// Throw Databinding Exception with error messages
			throw new DatabindingException(messages);
		}

		return employee;
	}

	// Update employee
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@PostMapping(value = "/updateEmployee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") String employeeId,
			@Valid @ModelAttribute EditEmployeeCommand editEmployeeCommand, BindingResult result)
			throws EmployeeNotFoundException, DatabindingException {

		String message = null;
		HttpStatus status = null;

		if (employeeService.isValidEmployeeId(employeeId)) {

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

				employeeTo.setEmployeeId(Integer.parseInt(employeeId));
				employeeTo.setRoleId(Integer.parseInt(editEmployeeCommand.getRole()));
				employeeTo.setJobTitleId(Integer.parseInt(editEmployeeCommand.getTitle()));
				employeeTo.setJoiningDate(editEmployeeCommand.getJoiningDate());

				// Update employee
				EmployeeTO employee = employeeService.updateEmployee(employeeTo);

				if (employee == null) {
					log.error("Employee  Not Found: " + employeeId);
					throw new EmployeeNotFoundException(employeeId.toString());
				} else {
					status = HttpStatus.OK;
					message = "Employee  " + employee.getFullName() + " Updated Successfully";
					log.info(message);
				}
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid Employee Id " + employeeId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);
	}

	// Archive employee
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@DeleteMapping(value = "/archiveEmployee/{id}")
	public ResponseEntity<String> archiveEmployee(@PathVariable("id") String employeeId)
			throws EmployeeNotFoundException, DatabindingException {

		EmployeeTO employeeTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeService.isValidEmployeeId(employeeId)) {
			employeeTO = employeeService.archiveEmployee(Integer.parseInt(employeeId));

			if (employeeTO == null) {
				log.error("Employee  Not Found: " + employeeId);
				throw new EmployeeNotFoundException(employeeId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee  " + employeeTO.getFullName() + " Archived Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid Employee Id " + employeeId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}

	// UnArchive employee
	// @PreAuthorize("hasAuthority('ROLE_Admin')")
	@PutMapping(value = "/unArchiveEmployee/{id}")
	public ResponseEntity<String> unArchiveEmployee(@PathVariable("id") String employeeId)
			throws EmployeeNotFoundException, DatabindingException {

		EmployeeTO employeeTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeService.isValidEmployeeId(employeeId)) {
			employeeTO = employeeService.unArchiveEmployee(Integer.parseInt(employeeId));

			if (employeeTO == null) {
				log.error("Employee  Not Found: " + employeeId);
				throw new EmployeeNotFoundException(employeeId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee  " + employeeTO.getFullName() + " UnArchived Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid Employee Id " + employeeId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}
}
