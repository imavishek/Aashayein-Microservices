/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.exception
 * @FileName: EmployeeTitleNotFoundException.java
 * @Author: Avishek Das
 * @CreatedDate: 15-07-2019
 * @Modified_By avishek.das @Last_On 15-Jul-2019 5:41:00 PM
 */

package com.aashayein.title.exception;

public class EmployeeTitleNotFoundException extends Exception {

	private static final long serialVersionUID = 8689709400525586660L;

	private String message;

	public EmployeeTitleNotFoundException() {
		this("Employee Title Not Found");
	}

	public EmployeeTitleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
