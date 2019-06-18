/**
 * @ProjectName: employee-service
 * @PackageName: com.aashayein.employee.dto
 * @FileName: ErrorResponse.java
 * @Author: Avishek Das
 * @CreatedDate: 17-06-2019
 * @Modified_By avishek.das @Last_On 17-Jun-2019 4:56:08 PM
 */

package com.aashayein.employee.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	private Integer status;

	private String error;

	private String message;

	private String path;
}
