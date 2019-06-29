/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: EmployeeEmailExistsException.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 4:02:46 PM
 */

package com.aashayein.employee.exception;

public class EmployeeEmailExistsException extends Exception {

	private static final long serialVersionUID = -3272826291471897126L;

	private String message;

	public EmployeeEmailExistsException() {
		this("Employee Email Exists");
	}

	public EmployeeEmailExistsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
