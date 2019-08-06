/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: BadRequestException.java
 * @Author: Avishek Das
 * @CreatedDate: 04-08-2019
 * @Modified_By avishek.das @Last_On 04-Aug-2019 12:06:20 AM
 */

package com.aashayein.employee.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 3311797201615582098L;

	private String message;

	public BadRequestException() {
		this("Bad Request");
	}

	public BadRequestException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
