/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.service
 * @FileName: EmployeeProfileService.java
 * @Author: Avishek Das
 * @CreatedDate: 19-07-2019
 * @Modified_By avishek.das @Last_On 19-Jul-2019 9:05:21 PM
 */

package com.aashayein.employee.service;

import com.aashayein.employee.dto.EmployeeTO;
import com.aashayein.employee.exception.EmployeeMobileNumberExistsException;
import com.aashayein.employee.exception.UploadingFailedException;

public interface EmployeeProfileService {

	EmployeeTO updateEmployeeProfile(EmployeeTO employeeTo)
			throws EmployeeMobileNumberExistsException, UploadingFailedException;

	EmployeeTO setPassword(EmployeeTO employeeTo);

}
