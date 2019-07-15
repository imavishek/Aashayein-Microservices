/**
 * @ProjectName: title-service
 * @PackageName: com.aashayein.title.dto
 * @FileName: EmployeeTitleTO.java
 * @Author: Avishek Das
 * @CreatedDate: 14-07-2019
 * @Modified_By avishek.das @Last_On 14-Jul-2019 2:09:59 PM
 */

package com.aashayein.title.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeTitleTO {

	private Integer titleId;

	private String titleName;

	private Byte archive;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordCreated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+5:30")
	private Date recordUpdated;
}
