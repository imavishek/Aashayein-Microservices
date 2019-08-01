/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: InvalidTokenException.java
 * @Author: Avishek Das
 * @CreatedDate: 23-07-2019
 * @Modified_By avishek.das @Last_On 23-Jul-2019 12:11:45 AM
 */

package com.aashayein.employee.exception;

public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = -3880213814828759813L;

	private String message;

	public InvalidTokenException() {
		this("Invalid Token");
	}

	public InvalidTokenException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
