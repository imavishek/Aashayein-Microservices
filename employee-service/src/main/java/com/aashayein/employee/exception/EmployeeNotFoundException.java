/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: EmployeeNotFoundException.java
 * @Author: Avishek Das
 * @CreatedDate: 01-07-2019
 * @Modified_By avishek.das @Last_On 01-Jul-2019 7:35:58 PM
 */

package com.aashayein.employee.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 7790620246985776899L;

	private String message;

	public EmployeeNotFoundException() {
		this("Employee Not Found");
	}

	public EmployeeNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
