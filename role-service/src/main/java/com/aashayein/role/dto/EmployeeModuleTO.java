/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.dto
 * @FileName: EmployeeModuleTO.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:01:57 PM
 */

package com.aashayein.role.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeModuleTO {

	private Integer moduleId;

	private String moduleName;

	private String moduleDirectory;

	private Byte archive;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordCreated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordUpdated;
}