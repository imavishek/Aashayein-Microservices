/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.service
 * @FileName: EmployeeRegistrationService.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 1:02:35 AM
 */

package com.aashayein.employee.service;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.EmployeeEmailExistsException;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.SMTPException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeRegistrationService {

	EmployeeTO addEmployee(EmployeeTO employeeTo) throws EmployeeEmailExistsException,
			EmployeeMobileNumberExistsException, SMTPException, UploadingFailedException, JsonProcessingException;
}