/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.serviceImpl
 * @FileName: EmployeeProfileServiceImpl.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:05:31 PM
 */

package com.aashayein.employee.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.repository.EmployeeAddressRepository;
import com.aashayein.employee.repository.EmployeeRepository;
import com.aashayein.employee.service.EmployeeProfileService;
import com.aashayein.employee.service.EmployeeService;
import com.aashayein.employee.util.ConvertEntityToTransferObject;
import com.aashayein.employee.util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeAddressRepository employeeAddressRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

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
	public EmployeeTO setPassword(EmployeeTO employeeTo) {
		return null;
	}

}
