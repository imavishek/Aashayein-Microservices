/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: EmployeeMobileNumberExistsException.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 4:03:02 PM
 */

package com.aashayein.employee.exception;

public class EmployeeMobileNumberExistsException extends Exception {

	private static final long serialVersionUID = -3637708849035437278L;

	private String message;

	public EmployeeMobileNumberExistsException() {
		this("Employee MobileNumber Exists");
	}

	public EmployeeMobileNumberExistsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
