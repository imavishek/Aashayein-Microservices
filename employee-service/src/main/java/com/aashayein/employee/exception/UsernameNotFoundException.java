/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: UsernameNotFoundException.java
 * @Author: Avishek Das
 * @CreatedDate: 03-08-2019
 * @Modified_By avishek.das @Last_On 03-Aug-2019 11:54:59 PM
 */

package com.aashayein.employee.exception;

public class UsernameNotFoundException extends Exception {

	private static final long serialVersionUID = -6590738015220226324L;

	private String message;

	public UsernameNotFoundException() {
		this("Username Not Found");
	}

	public UsernameNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
