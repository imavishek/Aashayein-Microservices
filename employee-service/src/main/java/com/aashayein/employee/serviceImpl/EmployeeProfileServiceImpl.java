/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.serviceImpl
 * @FileName: EmployeeProfileServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:05:31 PM
 */

package com.aashayein.employee.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.entities.City;
import com.aashayein.employee.entities.Country;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.entities.EmployeeAddress;
import com.aashayein.employee.entities.EmployeeRole;
import com.aashayein.employee.entities.EmployeeTitle;
import com.aashayein.employee.entities.State;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.InvalidTokenException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.exception.UsernameNotFoundException;
import com.aashayein.employee.rabbitMQ_sender.ActivationLinkMailSender;
import com.aashayein.employee.rabbitMQ_sender.ActivationSuccessfulMailSender;
import com.aashayein.employee.rabbitMQ_sender.ResetLinkMailSender;
import com.aashayein.employee.rabbitMQ_sender.ResetSuccessfulMailSender;
import com.aashayein.employee.repository.EmployeeAddressRepository;
import com.aashayein.employee.repository.EmployeeRepository;
import com.aashayein.employee.service.EmployeeProfileService;
import com.aashayein.employee.service.EmployeeService;
import com.aashayein.employee.util.ConvertEntityToTransferObject;
import com.aashayein.employee.util.DateTime;
import com.aashayein.employee.util.FileUploadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeAddressRepository employeeAddressRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	DateTime dateTime;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Autowired
	private ResetSuccessfulMailSender resetSuccessfulMailSender;

	@Autowired
	private ActivationSuccessfulMailSender activationSuccessfulMailSender;

	@Autowired
	private ResetLinkMailSender resetLinkMailSender;

	@Autowired
	private ActivationLinkMailSender activationLinkMailSender;

	@Override
	@Transactional
	public EmployeeTO updateEmployeeProfile(EmployeeTO employeeTo)
			throws EmployeeMobileNumberExistsException, UploadingFailedException {
		Employee employee = null;
		EmployeeTitle employeeTitle = null;
		EmployeeRole employeeRole = null;
		EmployeeAddress address = null;
		Country country = null;
		State state = null;
		City city = null;
		String fileName = null;

		employee = employeeRepository.findByEmployeeId(employeeTo.getEmployeeId());

		if (employee == null) {
			return null;
		}

		// If mobile number changed then check the existence of mobile number
		if (!employee.getMobileNumber().equalsIgnoreCase(employeeTo.getMobileNumber())
				&& employeeService.getEmployeeByMobileNumber(employeeTo.getMobileNumber()) != null) {
			log.error("Employee MobileNumber Exists: " + employeeTo.getMobileNumber());
			throw new EmployeeMobileNumberExistsException(employeeTo.getMobileNumber());
		}

		// save profile photo in server, rename the file with UUID
		if (!employeeTo.getProfilePhotoFile().isEmpty()) {
			fileName = fileUploadUtil.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(),
					employee.getEmployeeCode());

			if (fileName == null) {
				log.info("Failed to upload profile picture.");
				throw new UploadingFailedException("Failed to upload profile picture.");
			}
		}
		employeeTo.setProfilePhoto(fileName);

		// Setting the Country entity value
		country = new Country();
		country.setCountryId(employeeTo.getCountryId());

		// Setting the State entity value
		state = new State();
		state.setStateId(employeeTo.getStateId());

		// Setting the City entity value
		city = new City();
		city.setCityId(employeeTo.getCityId());

		/*
		 * If address not available then create EmployeeAddress entity otherwise get the
		 * existing EmployeeAddress entity from employee details
		 */
		if (employee.getAddress() == null) {
			address = new EmployeeAddress();
		} else {
			address = employee.getAddress();
		}

		// Setting the Address entity value
		address.setCountry(country);
		address.setState(state);
		address.setCity(city);
		address.setPostalCode(employeeTo.getPostalCode());
		address.setAddressLine1(employeeTo.getAddressLine1());
		address.setAddressLine2(employeeTo.getAddressLine2().isEmpty() ? null : employeeTo.getAddressLine2());

		// Setting the Title entity value
		employeeTitle = new EmployeeTitle();
		employeeTitle.setTitleId(employeeTo.getJobTitleId());

		// Setting the Role entity value
		employeeRole = new EmployeeRole();
		employeeRole.setRoleId(employeeTo.getRoleId());

		employee.setFirstName(employeeTo.getFirstName());
		employee.setMiddleName(employeeTo.getMiddleName().isEmpty() ? null : employeeTo.getMiddleName());
		employee.setLastName(employeeTo.getLastName());
		employee.setGender(employeeTo.getGender());
		employee.setMobileNumber(employeeTo.getMobileNumber());
		employee.setAlternateMobileNumber(
				employeeTo.getAlternateMobileNumber().isEmpty() ? null : employeeTo.getAlternateMobileNumber());
		employee.setAlternateEmail(employeeTo.getAlternateEmail().isEmpty() ? null : employeeTo.getAlternateEmail());
		employee.setAddress(address);
		employee.setProfilePhoto(employeeTo.getProfilePhoto().isEmpty() ? null : employeeTo.getProfilePhoto());

		employeeAddressRepository.save(address);

		employee = employeeRepository.save(employee);

		employee.setFullName(employee.getFirstName() + " " + employee.getLastName());

		employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);

		return employeeTo;
	}

	@Override
	@Transactional
	public void activeAccount(EmployeeTO employeeTo) throws InvalidTokenException, JsonProcessingException {

		Employee employee = null;

		employee = employeeRepository.findByTokenUUID(employeeTo.getTokenUUID());

		if (employee == null) {
			log.error("Invalid token to active account: " + employeeTo.getTokenUUID());
			throw new InvalidTokenException(employeeTo.getTokenUUID());
		}

		setPassword(employeeTo);

		log.info("Employee Activated Successfully For Employee Having EmployeeId: " + employee.getEmployeeId());

		activationSuccessfulMailSender.sendMail(convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee));
	}

	@Override
	@Transactional
	public void resetPassword(EmployeeTO employeeTo) throws InvalidTokenException, JsonProcessingException {

		Employee employee = null;

		employee = employeeRepository.findByTokenUUID(employeeTo.getTokenUUID());

		if (employee == null) {
			log.error("Invalid token to reset passsword: " + employeeTo.getTokenUUID());
			throw new InvalidTokenException(employeeTo.getTokenUUID());
		}

		setPassword(employeeTo);

		log.info("Password Reseted Successfully For Employee Having EmployeeId: " + employee.getEmployeeId());

		resetSuccessfulMailSender.sendMail(convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee));
	}

	@Override
	@Transactional
	public void sendActivationLink(String username) throws UsernameNotFoundException, JsonProcessingException {
		Employee employee = null;
		EmployeeTO employeeTo = null;

		employee = employeeRepository.findByEmail(username);

		if (employee == null) {
			log.error("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}

		employee.setTokenUUID(UUID.randomUUID().toString());
		employee.setTokenGeneratedDate(dateTime.getCurrentDateTime());

		employee = employeeRepository.save(employee);

		employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);
		employeeTo.setTokenUUID(employee.getTokenUUID());
		employeeTo.setTokenGeneratedDate(employee.getTokenGeneratedDate());

		activationLinkMailSender.sendMail(employeeTo);

	}

	@Override
	@Transactional
	public void sendResetPasswordLink(String username) throws UsernameNotFoundException, JsonProcessingException {
		Employee employee = null;
		EmployeeTO employeeTo = null;

		employee = employeeRepository.findByEmail(username);

		if (employee == null) {
			log.error("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}

		employee.setTokenUUID(UUID.randomUUID().toString());
		employee.setTokenGeneratedDate(dateTime.getCurrentDateTime());

		employee = employeeRepository.save(employee);

		employeeTo = convertEntityToTransferObject.convertEmployeeToEmployeeTO(employee);
		employeeTo.setTokenUUID(employee.getTokenUUID());
		employeeTo.setTokenGeneratedDate(employee.getTokenGeneratedDate());

		resetLinkMailSender.sendMail(employeeTo);

	}

	private void setPassword(EmployeeTO employeeTo) {
		Employee employee = null;

		employee = employeeRepository.findByTokenUUID(employeeTo.getTokenUUID());
		employee.setPassword(passwordEncoder.encode(employeeTo.getPassword()));
		employee.setTokenUUID(null);
		employee.setTokenGeneratedDate(null);

		employeeRepository.save(employee);

	}

}
