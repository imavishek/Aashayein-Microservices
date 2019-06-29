/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: ConvertTransferObjectToEntity.java
 * @Author: Avishek Das
 * @CreatedDate: 27-06-2019
 * @Modified_By avishek.das @Last_On 27-Jun-2019 12:57:27 AM
 */

package com.aashayein.employee.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.entities.EmployeeRole;
import com.aashayein.employee.entities.EmployeeTitle;

@Component
public class ConvertTransferObjectToEntity {

	@Autowired
	DateTime dateTime;

	public Employee convertEmployeeTOToEmployee(EmployeeTO employeeTo) {
		Employee employee = new Employee();

		if (employeeTo != null) {
			EmployeeTitle employeeTitle = new EmployeeTitle();
			employeeTitle.setTitleId(employeeTo.getJobTitleId());

			EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setRoleId(employeeTo.getRoleId());

			employee.setEmployeeCode(employeeTo.getEmployeeCode());
			employee.setFirstName(employeeTo.getFirstName());
			employee.setMiddleName(employeeTo.getMiddleName().isEmpty() ? null : employeeTo.getMiddleName());
			employee.setLastName(employeeTo.getLastName());
			employee.setGender(employeeTo.getGender());
			employee.setMobileNumber(employeeTo.getMobileNumber());
			employee.setAlternateMobileNumber(
					employeeTo.getAlternateMobileNumber().isEmpty() ? null : employeeTo.getAlternateMobileNumber());
			employee.setEmail(employeeTo.getEmail());
			employee.setAlternateEmail(
					employeeTo.getAlternateEmail().isEmpty() ? null : employeeTo.getAlternateEmail());
			employee.setTitle(employeeTitle);
			employee.setRole(employeeRole);
			employee.setProfilePhoto(employeeTo.getProfilePhoto());
			employee.setTokenUUID(UUID.randomUUID().toString());
			employee.setTokenGeneratedDate(dateTime.getCurrentDateTime());
			employee.setJoiningDate(employeeTo.getJoiningDate());
		}

		return employee;
	}
}
