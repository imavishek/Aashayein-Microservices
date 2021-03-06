/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.service
 * @FileName: EmployeeService.java
 * @Author: Avishek Das
 * @CreatedDate: 12-06-2019
 * @Modified_By avishek.das @Last_On 12-Jun-2019 3:57:04 PM
 */

package com.aashayein.employee.service;

import java.util.List;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.DatabindingException;

public interface EmployeeService {

	List<EmployeeTO> getAllEmployees();

	EmployeeTO getEmployeeById(Integer employeeId);

	EmployeeTO getEmployeeByEmail(String email);

	EmployeeTO getEmployeeByMobileNumber(String mobileNumber);

	EmployeeTO updateEmployee(EmployeeTO employeeTo);

	EmployeeTO archiveEmployee(Integer employeeId);

	EmployeeTO unArchiveEmployee(Integer employeeId);

	boolean isValidEmployeeId(String employeeId) throws DatabindingException;

}
