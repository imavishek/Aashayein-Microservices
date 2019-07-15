/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.controller
 * @FileName: EmployeeTitleController.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:18:35 PM
 */

package com.aashayein.title.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

import com.aashayein.title.command.EmployeeTitleCommand;
import com.aashayein.title.dto.EmployeeTitleTO;
import com.aashayein.title.exception.DatabindingException;
import com.aashayein.title.exception.EmployeeTitleNotFoundException;
import com.aashayein.title.service.EmployeeTitleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/Admin/EmployeeTitle")
@Slf4j
public class EmployeeTitleController {

	@Autowired
	private EmployeeTitleService employeeTitleService;

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "titleName", new StringTrimmerEditor(false));
	}

	// Get all employee titles
	@GetMapping(value = "/getJobTitles")
	public List<EmployeeTitleTO> getJobTitles() {

		return employeeTitleService.getJobTitles();

	}

	// Get employee title by employeeTitleId
	@GetMapping(value = "/getTitleById/{id}")
	public EmployeeTitleTO getTitleById(@PathVariable("id") String employeeTitleId)
			throws DatabindingException, EmployeeTitleNotFoundException {

		EmployeeTitleTO employeeTitleTO = null;

		if (employeeTitleService.isValidEmployeeTitleId(employeeTitleId)) {
			employeeTitleTO = employeeTitleService.getEmployeeTitleById(Integer.parseInt(employeeTitleId));

			if (employeeTitleTO == null) {
				throw new EmployeeTitleNotFoundException(employeeTitleId.toString());
			}
		} else {
			log.error("Invalid EmployeeTitle Id");
			throw new EmployeeTitleNotFoundException(employeeTitleId.toString());
		}

		return employeeTitleTO;
	}

	// Add emploee title
	@PostMapping(value = "/addTitle")
	public ResponseEntity<String> addEmployeeTitle(@Valid @ModelAttribute EmployeeTitleCommand employeeTitleCommand,
			BindingResult result) throws DatabindingException {

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

			// Setting value in EmployeeTitle Transfer Object
			EmployeeTitleTO employeeTitleTo = new EmployeeTitleTO();

			employeeTitleTo.setTitleName(employeeTitleCommand.getTitleName());

			// Adding employee title
			EmployeeTitleTO employeeTitle = employeeTitleService.addEmployeeTitle(employeeTitleTo);

			if (employeeTitle == null) {
				status = HttpStatus.BAD_REQUEST;
				message = "Failed To Add Employee Title " + employeeTitleCommand.getTitleName();
				log.error(message);
			} else {
				status = HttpStatus.OK;
				message = "Employee Title " + employeeTitleCommand.getTitleName() + " Added Successfully";
				log.info(message);
			}

			return new ResponseEntity<>(message, status);
		}

	}

	// Update employee title
	@PutMapping(value = "/updateTitle/{id}")
	public ResponseEntity<String> updateEmployeeTitle(@PathVariable("id") String employeeTitleId,
			@Valid @ModelAttribute EmployeeTitleCommand employeeTitleCommand, BindingResult result)
			throws EmployeeTitleNotFoundException, DatabindingException {

		String message = null;
		HttpStatus status = null;

		if (employeeTitleService.isValidEmployeeTitleId(employeeTitleId)) {

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

				// Setting value in EmployeeTitle Transfer Object
				EmployeeTitleTO employeeTitleTo = new EmployeeTitleTO();

				employeeTitleTo.setTitleId(Integer.parseInt(employeeTitleId));
				employeeTitleTo.setTitleName(employeeTitleCommand.getTitleName());

				// Delete employee title
				EmployeeTitleTO employeeTitle = employeeTitleService.updateEmployeeTitle(employeeTitleTo);

				if (employeeTitle == null) {
					log.error("Employee Title Not Found: " + employeeTitleId);
					throw new EmployeeTitleNotFoundException(employeeTitleId.toString());
				} else {
					status = HttpStatus.OK;
					message = "Employee Title " + employeeTitleCommand.getTitleName() + " Updated Successfully";
					log.info(message);
				}
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeTitle Id " + employeeTitleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);
	}

	// Delete employee title
	@DeleteMapping(value = "/deleteTitle/{id}")
	public ResponseEntity<String> deleteEmployeeTitle(@PathVariable("id") String employeeTitleId)
			throws EmployeeTitleNotFoundException, DatabindingException {

		EmployeeTitleTO employeeTitleTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeTitleService.isValidEmployeeTitleId(employeeTitleId)) {
			employeeTitleTO = employeeTitleService.deleteEmployeeTitle(Integer.parseInt(employeeTitleId));

			if (employeeTitleTO == null) {
				log.error("Employee Title Not Found: " + employeeTitleId);
				throw new EmployeeTitleNotFoundException(employeeTitleId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee Title " + employeeTitleTO.getTitleName() + " Deleted Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeTitle Id " + employeeTitleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}

	// Active employee title
	@PutMapping(value = "/activeTitle/{id}")
	public ResponseEntity<String> activeEmployeeTitle(@PathVariable("id") String employeeTitleId)
			throws EmployeeTitleNotFoundException, DatabindingException {

		EmployeeTitleTO employeeTitleTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeTitleService.isValidEmployeeTitleId(employeeTitleId)) {
			employeeTitleTO = employeeTitleService.activeEmployeeTitle(Integer.parseInt(employeeTitleId));

			if (employeeTitleTO == null) {
				log.error("Employee Title Not Found: " + employeeTitleId);
				throw new EmployeeTitleNotFoundException(employeeTitleId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee Title " + employeeTitleTO.getTitleName() + " Activated Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeTitle Id " + employeeTitleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}

}
