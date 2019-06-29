/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.exception
 * @FileName: UploadingFailedException.java
 * @Author: Avishek Das
 * @CreatedDate: 26-06-2019
 * @Modified_By avishek.das @Last_On 26-Jun-2019 10:53:39 PM
 */

package com.aashayein.employee.exception;

public class UploadingFailedException extends Exception {

	private static final long serialVersionUID = -8375590028877737571L;

	private String message;

	public UploadingFailedException() {
		this("Failed to upload file");
	}

	public UploadingFailedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
