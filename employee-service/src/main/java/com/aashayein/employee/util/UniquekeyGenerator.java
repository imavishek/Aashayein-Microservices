/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.util
 * @FileName: UniquekeyGenerator.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 10:35:21 PM
 */

package com.aashayein.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aashayein.employee.entities.Employee;
import com.aashayein.employee.repository.EmployeeRepository;

@Component
public class UniquekeyGenerator {

	@Autowired
	private EmployeeRepository employeeRepository;

	public String getNextEmployeeCode() {

		String employeeCode = null;
		Integer lastEmployeeId = null;

		Employee employee = employeeRepository.findTopByOrderByEmployeeIdDesc();
		lastEmployeeId = employee.getEmployeeId();

		/*
		 * For 1st employee the employeeCode is "asha-0001" after that it increased by 1
		 */
		if (lastEmployeeId == null) {
			employeeCode = "asha-00001";
		} else {

			++lastEmployeeId;

			if (lastEmployeeId <= 9) {
				employeeCode = "asha-0000" + lastEmployeeId;
			} else if (lastEmployeeId <= 99) {
				employeeCode = "asha-000" + lastEmployeeId;
			} else if (lastEmployeeId <= 999) {
				employeeCode = "asha-00" + lastEmployeeId;
			} else if (lastEmployeeId <= 9999) {
				employeeCode = "asha-0" + lastEmployeeId;
			} else {
				employeeCode = "asha-" + lastEmployeeId;
			}
		}

		return employeeCode;
	}
}
