/**
 * @ProjectName: role-service
 * @PackageName: com.aashayein.role.dto
 * @FileName: EmployeeRoleTO.java
 * @Author: Avishek Das
 * @CreatedDate: 16-07-2019
 * @Modified_By avishek.das @Last_On 16-Jul-2019 9:02:13 PM
 */

package com.aashayein.role.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmployeeRoleTO {

	private Integer roleId;

	private String roleName;

	private Byte archive;

	private List<EmployeeModuleTO> accessModules;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordCreated;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5:30")
	private Date recordUpdated;
}
