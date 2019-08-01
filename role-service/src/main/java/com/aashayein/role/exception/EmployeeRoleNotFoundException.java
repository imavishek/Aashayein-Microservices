/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.exception
 * @FileName: EmployeeRoleNotFoundException.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:43:37 PM
 */

package com.aashayein.role.exception;

public class EmployeeRoleNotFoundException extends Exception {

	private static final long serialVersionUID = 142387296608612884L;

	private String message;

	public EmployeeRoleNotFoundException() {
		this("Employee Role Not Found");
	}

	public EmployeeRoleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
