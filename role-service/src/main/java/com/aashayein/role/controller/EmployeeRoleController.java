/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.controller
 * @FileName: EmployeeRoleController.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:05:07 PM
 */

package com.aashayein.role.controller;

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

import com.aashayein.role.command.EmployeeRoleCommand;
import com.aashayein.role.dto.EmployeeModuleTO;
import com.aashayein.role.dto.EmployeeRoleTO;
import com.aashayein.role.exception.DatabindingException;
import com.aashayein.role.exception.EmployeeRoleNotFoundException;
import com.aashayein.role.service.EmployeeRoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/Admin/EmployeeRole")
public class EmployeeRoleController {

	@Autowired
	private EmployeeRoleService employeeRoleService;

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "roleName", new StringTrimmerEditor(false));
	}

	// Get all employee roles
	@GetMapping(value = "/getRoles")
	public List<EmployeeRoleTO> getEmployeeRoles() {

		return employeeRoleService.getEmployeeRoles();
	}

	// Get employee role by employeeRoleId
	@GetMapping(value = "/getRoleById/{id}")
	public EmployeeRoleTO getRoleById(@PathVariable("id") String employeeRoleId)
			throws DatabindingException, EmployeeRoleNotFoundException {

		EmployeeRoleTO employeeRoleTo = null;

		if (employeeRoleService.isValidEmployeeRoleId(employeeRoleId)) {
			employeeRoleTo = employeeRoleService.getEmployeeRoleById(Integer.parseInt(employeeRoleId));

			if (employeeRoleTo == null) {
				throw new EmployeeRoleNotFoundException(employeeRoleId.toString());
			}
		} else {
			log.error("Invalid EmployeeRole Id");
			throw new EmployeeRoleNotFoundException(employeeRoleId.toString());
		}

		return employeeRoleTo;
	}

	// Add emploee role
	@PostMapping(value = "/addRole")
	public ResponseEntity<String> addEmployeeRole(@Valid @ModelAttribute EmployeeRoleCommand employeeRoleCommand,
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
			// Setting value in EmployeeRole Transfer Object
			EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();
			List<EmployeeModuleTO> accessModules = new ArrayList<EmployeeModuleTO>();

			for (String moduleId : employeeRoleCommand.getModuleIds()) {
				EmployeeModuleTO employeeModuleTO = new EmployeeModuleTO();
				employeeModuleTO.setModuleId(Integer.parseInt(moduleId));

				accessModules.add(employeeModuleTO);
			}

			employeeRoleTO.setRoleName(employeeRoleCommand.getRoleName());
			employeeRoleTO.setAccessModules(accessModules);

			// Adding employee Role
			EmployeeRoleTO employeeRole = employeeRoleService.addEmployeeRole(employeeRoleTO);

			if (employeeRole == null) {
				status = HttpStatus.BAD_REQUEST;
				message = "Failed To Add Employee Role " + employeeRoleCommand.getRoleName();
				log.error(message);
			} else {
				status = HttpStatus.OK;
				message = "Employee Role " + employeeRoleCommand.getRoleName() + " Added Successfully";
				log.info(message);
			}

			return new ResponseEntity<>(message, status);
		}

	}

	// Update employee role
	@PutMapping(value = "/updateRole/{id}")
	public ResponseEntity<String> updateEmployeeRole(@PathVariable("id") String employeeRoleId,
			@Valid @ModelAttribute EmployeeRoleCommand employeeRoleCommand, BindingResult result)
			throws EmployeeRoleNotFoundException, DatabindingException {

		String message = null;
		HttpStatus status = null;

		if (employeeRoleService.isValidEmployeeRoleId(employeeRoleId)) {

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
				// Setting value in EmployeeRole Transfer Object
				EmployeeRoleTO employeeRoleTO = new EmployeeRoleTO();
				List<EmployeeModuleTO> accessModules = new ArrayList<EmployeeModuleTO>();

				for (String moduleId : employeeRoleCommand.getModuleIds()) {
					EmployeeModuleTO employeeModuleTO = new EmployeeModuleTO();
					employeeModuleTO.setModuleId(Integer.parseInt(moduleId));

					accessModules.add(employeeModuleTO);
				}

				employeeRoleTO.setRoleId(Integer.parseInt(employeeRoleId));
				employeeRoleTO.setRoleName(employeeRoleCommand.getRoleName());
				employeeRoleTO.setAccessModules(accessModules);

				// Update employee Role
				EmployeeRoleTO employeeRole = employeeRoleService.updateEmployeeRole(employeeRoleTO);

				if (employeeRole == null) {
					log.error("Employee Role Not Found: " + employeeRoleId);
					throw new EmployeeRoleNotFoundException(employeeRoleId.toString());
				} else {
					status = HttpStatus.OK;
					message = "Employee Role " + employeeRoleCommand.getRoleName() + " Updated Successfully";
					log.info(message);
				}
			}
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeRole Id " + employeeRoleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);
	}

	// Delete employee role
	@DeleteMapping(value = "/deleteRole/{id}")
	public ResponseEntity<String> deleteEmployeeRole(@PathVariable("id") String employeeRoleId)
			throws EmployeeRoleNotFoundException, DatabindingException {

		EmployeeRoleTO employeeRoleTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeRoleService.isValidEmployeeRoleId(employeeRoleId)) {
			employeeRoleTO = employeeRoleService.deleteEmployeeRole(Integer.parseInt(employeeRoleId));

			if (employeeRoleTO == null) {
				log.error("Employee Role Not Found: " + employeeRoleId);
				throw new EmployeeRoleNotFoundException(employeeRoleId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee Role " + employeeRoleTO.getRoleName() + " Deleted Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeRole Id " + employeeRoleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}

	// Active employee role
	@PutMapping(value = "/activeRole/{id}")
	public ResponseEntity<String> activeEmployeeRole(@PathVariable("id") String employeeRoleId)
			throws EmployeeRoleNotFoundException, DatabindingException {

		EmployeeRoleTO employeeRoleTO = null;
		String message = null;
		HttpStatus status = null;

		if (employeeRoleService.isValidEmployeeRoleId(employeeRoleId)) {
			employeeRoleTO = employeeRoleService.activeEmployeeRole(Integer.parseInt(employeeRoleId));

			if (employeeRoleTO == null) {
				log.error("Employee Role Not Found: " + employeeRoleId);
				throw new EmployeeRoleNotFoundException(employeeRoleId.toString());
			}

			status = HttpStatus.OK;
			message = "Employee Role " + employeeRoleTO.getRoleName() + " Activated Successfully";
			log.info(message);
		} else {
			status = HttpStatus.BAD_REQUEST;
			message = "Invalid EmployeeRole Id " + employeeRoleId;
			log.error(message);
		}

		return new ResponseEntity<>(message, status);

	}

}
