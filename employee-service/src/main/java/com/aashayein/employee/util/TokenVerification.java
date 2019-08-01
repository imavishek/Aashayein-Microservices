/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: TokenVerification.java
 * @Author: Avishek Das
 * @CreatedDate: 22-07-2019
 * @Modified_By avishek.das @Last_On 22-Jul-2019 11:52:34 PM
 */

package com.aashayein.employee.util;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.repository.EmployeeRepository;

@Component
public class TokenVerification {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Boolean isValidToken(String token, Long expiration) {

		Boolean isValid = false;

		Employee employee = employeeRepository.findByTokenUUID(token);

		if (employee == null) {
			return isValid;
		}

		// Validation token expiration time
		Calendar cal = Calendar.getInstance();

		if (cal.getTime().getTime() - employee.getTokenGeneratedDate().getTime() >= expiration) {
			return isValid;
		}

		isValid = true;

		return isValid;
	}
}
