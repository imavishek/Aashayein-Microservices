/**
 * @ProjectName: export-service
 * @PackageName: com.aashayein.export.dto
 * @FileName: ErrorResponse.java
 * @Author: Avishek Das
 * @CreatedDate: 20-06-2019
 * @Modified_By avishek.das @Last_On 20-Jun-2019 1:12:20 AM
 */

package com.aashayein.export.dto;

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
