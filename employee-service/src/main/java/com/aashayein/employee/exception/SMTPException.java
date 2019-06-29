/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: SMTPException.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 9:37:43 PM
 */

package com.aashayein.employee.exception;

public class SMTPException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	public SMTPException() {
		this("SMTP verification failed");
	}

	public SMTPException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
