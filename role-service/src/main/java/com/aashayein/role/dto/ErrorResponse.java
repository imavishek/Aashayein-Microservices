/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.dto
 * @FileName: ErrorResponse.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:42:40 PM
 */

package com.aashayein.role.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.aashayein.role.dto.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	private Integer status;

	private String error;

	private List<String> message;

	private String path;
}
