/**
 * @ProjectName: zuul-gateway
 * @PackageName: com.aashayein.zuul.dto
 * @FileName: ErrorResponse.java
 * @Author: Avishek Das
 * @CreatedDate: 18-06-2019
 * @Modified_By avishek.das @Last_On 18-Jun-2019 11:39:16 PM
 */

package com.aashayein.zuul.dto;

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
