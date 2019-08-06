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
import com.aashayein.employee.exception.InvalidTokenException;
import com.aashayein.employee.exception.UploadingFailedException;
import com.aashayein.employee.exception.UsernameNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeProfileService {

	EmployeeTO updateEmployeeProfile(EmployeeTO employeeTo)
			throws EmployeeMobileNumberExistsException, UploadingFailedException;

	void activeAccount(EmployeeTO employeeTo) throws InvalidTokenException, JsonProcessingException;

	void resetPassword(EmployeeTO employeeTo) throws InvalidTokenException, JsonProcessingException;

	void sendActivationLink(String username) throws UsernameNotFoundException, JsonProcessingException;

	void sendResetPasswordLink(String username) throws UsernameNotFoundException, JsonProcessingException;

}
