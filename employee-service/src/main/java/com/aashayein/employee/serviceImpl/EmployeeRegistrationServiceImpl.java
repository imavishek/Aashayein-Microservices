/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.serviceImpl
 * @FileName: EmployeeRegistrationService.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 1:04:12 AM
 */

package com.aashayein.employee.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.dto.MailCheckerTO;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.exception.EmployeeEmailExistsException;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.SMTPException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.rabbitMQ_sender.RegistrationMailSender;
import com.aashayein.employee.repository.EmployeeRepository;
import com.aashayein.employee.service.EmployeeRegistrationService;
import com.aashayein.employee.service.EmployeeService;
import com.aashayein.employee.util.ConvertEntityToTransferObject;
import com.aashayein.employee.util.ConvertTransferObjectToEntity;
import com.aashayein.employee.util.FileUploadUtil;
import com.aashayein.employee.util.Mailboxlayer;
import com.aashayein.employee.util.UniquekeyGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeRegistrationServiceImpl implements EmployeeRegistrationService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ConvertTransferObjectToEntity convertTransferObjectToEntity;

	@Autowired
	private ConvertEntityToTransferObject convertEntityToTransferObject;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Mailboxlayer mailboxlayer;

	@Autowired
	private UniquekeyGenerator uniquekeyGenerator;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@Autowired
	private RegistrationMailSender registrationMailSender;

	/*
	 * Save employee details, upload profile photo, and send registration success
	 * mail
	 */
	@Override
	@Transactional
	public EmployeeTO addEmployee(EmployeeTO employeeTo) throws EmployeeEmailExistsException,
			EmployeeMobileNumberExistsException, SMTPException, UploadingFailedException, JsonProcessingException {

		EmployeeTO employee = null;
		Employee emp = null;
		MailCheckerTO mailCheckerTO = null;
		String employeeCode = null;
		String fileName = null;

		// Checking the existence of Email and PhoneNo
		if (employeeService.getEmployeeByEmail(employeeTo.getEmail()) != null) {
			log.error("Employee Email Exists: " + employeeTo.getEmail());
			throw new EmployeeEmailExistsException(employeeTo.getEmail());

		} else if (employeeService.getEmployeeByMobileNumber(employeeTo.getMobileNumber()) != null) {
			log.error("Employee MobileNumber Exists: " + employeeTo.getMobileNumber());
			throw new EmployeeMobileNumberExistsException(employeeTo.getMobileNumber());
		}

		// SMTP verification of mailId
		mailCheckerTO = mailboxlayer.checkEmailExistence(employeeTo.getEmail());

		if (mailCheckerTO.getSmtp_check() == false && mailCheckerTO.getMx_found() == false) {
			log.info("SMTP verification failed for mailId: " + mailCheckerTO.getEmail());
			throw new SMTPException(employeeTo.getEmail());
		}

		// Getting the next employeecode
		employeeCode = uniquekeyGenerator.getNextEmployeeCode();
		employeeTo.setEmployeeCode(employeeCode);

		// save profile photo in server, rename the file with UUID
		if (!employeeTo.getProfilePhotoFile().isEmpty()) {
			fileName = fileUploadUtil.uploadProfilePictureIntoServer(employeeTo.getProfilePhotoFile(), employeeCode);

			if (fileName == null) {
				log.info("Failed to upload profile picture.");
				throw new UploadingFailedException("Failed to upload profile picture.");
			}
		}
		employeeTo.setProfilePhoto(fileName);

		emp = employeeRepository.save(convertTransferObjectToEntity.convertEmployeeTOToEmployee(employeeTo));

		if (emp == null) {
			log.info("Failed to save employee details in database");

			return employee;
		}

		emp.setFullName(emp.getFirstName() + " " + emp.getLastName());

		// Convert Employee entity to EmployeeTO
		employee = convertEntityToTransferObject.convertEmployeeToEmployeeTO(emp);

		// Set TokenUUID
		employee.setTokenUUID(emp.getTokenUUID());
		employee.setTokenGeneratedDate(emp.getTokenGeneratedDate());

		// Send registration success mail
		registrationMailSender.sendMail(employee);

		return employeeTo;
	}

}
