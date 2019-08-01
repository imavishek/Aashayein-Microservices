/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.controller
 * @FileName: EmployeeProfileController.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:04:29 PM
 */

package com.aashayein.employee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashayein.employee.command.EditEmployeeProfileCommand;
import com.aashayein.employee.command.PasswordCommand;
import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.DatabindingException;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.EmployeeNotFoundException;
import com.aashayein.employee.exception.InvalidTokenException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.propertyEditor.ReplaceSpaceEditor;
import com.aashayein.employee.service.EmployeeProfileService;
import com.aashayein.employee.service.EmployeeService;
import com.aashayein.employee.util.TokenVerification;

import lombok.extern.slf4j.Slf4j;

@RestController
// @PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/EmployeeProfile")
@Slf4j
@RefreshScope
public class EmployeeProfileController {

	@Autowired
	private EmployeeProfileService employeeProfileService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private TokenVerification tokenVerification;

	@Value("${expiration.time.activeaccount}")
	private Long expirationTimeActiveaccount;

	@Value("${expiration.time.resetpassword}")
	private Long expirationTimeResetpassword;

	@InitBinder
	public void customizeBinding(WebDataBinder binder) {

		binder.registerCustomEditor(String.class, "firstName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "middleName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "lastName", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "mobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateMobileNumber", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "alternateEmail", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "pinCode", new StringTrimmerEditor(false));
		binder.registerCustomEditor(String.class, "addressLine1", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "addressLine2", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "password", new ReplaceSpaceEditor());
		binder.registerCustomEditor(String.class, "confirmPassword", new ReplaceSpaceEditor());
	}

	// Get employee profile
	@GetMapping(value = "/getEmployeeProfile/{id}")
	public EmployeeTO getEmployeeProfile(@PathVariable("id") String id)
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

	// Update employee profile
	@PostMapping(value = "/updateEmployeeProfile/{id}")
	public ResponseEntity<String> updateEmployeeProfile(@PathVariable("id") String employeeId,
			@Valid @ModelAttribute EditEmployeeProfileCommand editEmployeeProfileCommand, BindingResult result)
			throws EmployeeNotFoundException, DatabindingException, EmployeeMobileNumberExistsException,
			UploadingFailedException {

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
				employeeTo.setFirstName(editEmployeeProfileCommand.getFirstName());
				employeeTo.setMiddleName(editEmployeeProfileCommand.getMiddleName());
				employeeTo.setLastName(editEmployeeProfileCommand.getLastName());
				employeeTo.setGender(editEmployeeProfileCommand.getGender());
				employeeTo.setMobileNumber(editEmployeeProfileCommand.getMobileNumber());
				employeeTo.setAlternateMobileNumber(editEmployeeProfileCommand.getAlternateMobileNumber());
				employeeTo.setAlternateEmail(editEmployeeProfileCommand.getAlternateEmail());
				employeeTo.setCountryId(Integer.parseInt(editEmployeeProfileCommand.getCountry()));
				employeeTo.setStateId(Integer.parseInt(editEmployeeProfileCommand.getState()));
				employeeTo.setCityId(Integer.parseInt(editEmployeeProfileCommand.getCity()));
				employeeTo.setPostalCode(editEmployeeProfileCommand.getPinCode());
				employeeTo.setAddressLine1(editEmployeeProfileCommand.getAddressLine1());
				employeeTo.setAddressLine2(editEmployeeProfileCommand.getAddressLine2());
				employeeTo.setProfilePhotoFile(editEmployeeProfileCommand.getProfilePhoto());

				// Update employee
				EmployeeTO employee = employeeProfileService.updateEmployeeProfile(employeeTo);

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

	// Active Employee Account
	@PostMapping(value = "/activeAccount/{token}")
	public ResponseEntity<String> activeAccount(@PathVariable("token") String token,
			@Valid @ModelAttribute PasswordCommand passwordCommand, BindingResult result)
			throws DatabindingException, InvalidTokenException {

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

			// Verify token and expired date
			if (!tokenVerification.isValidToken(token, expirationTimeActiveaccount)) {
				log.error("Invalid Token");
				throw new InvalidTokenException(token);
			}

			// Setting value in Employee Transfer Object
			EmployeeTO employeeTo = new EmployeeTO();

			employeeTo.setTokenUUID(token);
			employeeTo.setPassword(passwordCommand.getPassword());

			// Set Profile Password
			EmployeeTO employee = employeeProfileService.setPassword(employeeTo);

			if (employee == null) {
				status = HttpStatus.BAD_REQUEST;
				message = "Unable To Update Password";
				log.info(message);
			} else {
				status = HttpStatus.OK;
				message = "Password Updated Successfully For Employee Having EmployeeId: " + employee.getEmployeeId();
				log.info(message);
				log.info("Employee Activated Successfully For Employee Having EmployeeId: " + employee.getEmployeeId());
			}
		}

		return new ResponseEntity<>(message, status);
	}
}
