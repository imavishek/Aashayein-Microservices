/**
 * @ProjectName: oauth-server
 * @PackageName: com.aashayein.oauth.dto
 * @FileName: ErrorResponse.java
 * @Author: Avishek Das
 * @CreatedDate: 29-07-2019
 * @Modified_By avishek.das @Last_On 29-Jul-2019 12:07:31 PM
 */

package com.aashayein.oauth.dto;

import java.time.LocalDateTime;
import java.util.List;

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
